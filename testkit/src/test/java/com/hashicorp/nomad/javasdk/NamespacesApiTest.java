package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.Namespace;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.List;

public class NamespacesApiTest extends ApiTestBase {

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToRegisterANamespace() throws Exception {
        try (TestAgent agent = newServer()) {
            NamespacesApi namespacesApi = agent.getApiClient().getNamespacesApi();

            final Namespace namespace = new Namespace()
                    .setName("blue")
                    .setDescription("Blue Things");

            final ServerResponse<Void> registerResponse = namespacesApi.register(namespace);
            assertUpdatedServerResponse(registerResponse);

            final ServerQueryResponse<List<Namespace>> namespacesResponse = namespacesApi.list();
            assertUpdatedServerResponse(namespacesResponse);

            final List<Namespace> namespaces = namespacesResponse.getValue();
            assertThat(namespaces, hasSize(2));
            assertThat(namespaces.get(0).getName(), is(namespace.getName()));
            assertThat(namespaces.get(1).getName(), is("default"));
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldNotBeAbleToRegisterAnInvalidNamespace() throws Exception {
        try (TestAgent agent = newServer()) {
            final NamespacesApi namespacesApi = agent.getApiClient().getNamespacesApi();

            new ErrorResponseAssertion("Invalid namespace") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return namespacesApi.register(new Namespace().setName("*"));
                }
            };
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToRetrieveNamespaces() throws Exception {
        try (TestAgent agent = newServer()) {
            final NamespacesApi namespacesApi = agent.getApiClient().getNamespacesApi();

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return namespacesApi.info("blue");
                }
            };

            final Namespace namespace = new Namespace()
                    .setName("blue")
                    .setDescription("Blue Things");

            namespacesApi.register(namespace);

            final ServerQueryResponse<Namespace> namespacesResponse = namespacesApi.info("blue");
            assertUpdatedServerResponse(namespacesResponse);

            final Namespace retrievedNamespace = namespacesResponse.getValue();
            assertThat(retrievedNamespace.getName(), is(namespace.getName()));
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToDeleteANamespace() throws Exception {
        try (TestAgent agent = newServer()) {
            final NamespacesApi namespacesApi = agent.getApiClient().getNamespacesApi();

            namespacesApi.register(new Namespace()
                    .setName("blue")
                    .setDescription("Blue Things"));

            final Namespace namespace = namespacesApi.info("blue").getValue();
            assertThat(namespace.getDescription(), is("Blue Things"));

            final ServerResponse<Void> deleteResponse = namespacesApi.delete("blue");
            assertUpdatedServerResponse(deleteResponse);

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return namespacesApi.info("blue");
                }
            };

        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldListNamespacesInReverseCreationOrderAndRespectPrefixes() throws Exception {
        try (TestAgent agent = newServer()) {
            NamespacesApi namespacesApi = agent.getApiClient().getNamespacesApi();

            namespacesApi.register(new Namespace().setName("fooaaa"));
            namespacesApi.register(new Namespace().setName("foobbb"));

            final ServerQueryResponse<List<Namespace>> namespacesResponse = namespacesApi.list();
            assertUpdatedServerResponse(namespacesResponse);
            final List<Namespace> namespaces = namespacesResponse.getValue();
            assertThat(namespaces, hasSize(3));
            assertThat(namespaces.get(0).getName(), is("foobbb"));
            assertThat(namespaces.get(1).getName(), is("fooaaa"));
            assertThat(namespaces.get(2).getName(), is("default"));

            final List<Namespace> fooPrefixNamespaces = namespacesApi.list("foo").getValue();
            assertThat(fooPrefixNamespaces, hasSize(2));
            assertThat(fooPrefixNamespaces.get(0).getName(), is("foobbb"));
            assertThat(fooPrefixNamespaces.get(1).getName(), is("fooaaa"));

            final List<Namespace> fooaPrefixNamespaces = namespacesApi.list("fooa").getValue();
            assertThat(fooaPrefixNamespaces, hasSize(1));
            assertThat(fooaPrefixNamespaces.get(0).getName(), is("fooaaa"));
        }
    }

}
