package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.*;
import com.hashicorp.nomad.testutils.TestAgent;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QuotasApiTest extends ApiTestBase {

    protected QuotaSpec testQuotaSpec() {
        return new QuotaSpec()
                .addLimits(new QuotaLimit()
                        .setRegion("default")
                        .setRegionLimit(new Resources().setCpu(100)));
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToRegisterAQuotaSpecification() throws Exception {
        try (TestAgent agent = newServer()) {
            QuotasApi quotasApi = agent.getApiClient().getQuotasApi();

            final QuotaSpec quota = testQuotaSpec()
                    .setName("alpha")
                    .setDescription("Quota Alpha");

            final ServerResponse<Void> registerResponse = quotasApi.register(quota);
            assertUpdatedServerResponse(registerResponse);

            final ServerQueryResponse<List<QuotaSpec>> quotasResponse = quotasApi.list();
            assertUpdatedServerResponse(quotasResponse);

            final List<QuotaSpec> quotas = quotasResponse.getValue();
            assertThat(quotas, hasSize(1));
            assertThat(quotas.get(0).getName(), is(quota.getName()));
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldNotBeAbleToRegisterAnInvalidQuotaSpecification() throws Exception {
        try (TestAgent agent = newServer()) {
            final QuotasApi quotasApi = agent.getApiClient().getQuotasApi();

            new ErrorResponseAssertion("Invalid quota") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return quotasApi.register(testQuotaSpec().setName("*"));
                }
            };
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToRetrieveQuotaSpecifications() throws Exception {
        try (TestAgent agent = newServer()) {
            final QuotasApi quotasApi = agent.getApiClient().getQuotasApi();

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return quotasApi.info("alpha");
                }
            };

            final QuotaSpec quota = testQuotaSpec()
                    .setName("alpha")
                    .setDescription("Quota Alpha");

            quotasApi.register(quota);

            final ServerQueryResponse<QuotaSpec> quotasResponse = quotasApi.info("alpha");
            assertUpdatedServerResponse(quotasResponse);

            final QuotaSpec retrievedQuota = quotasResponse.getValue();
            assertThat(retrievedQuota.getName(), is(quota.getName()));
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToRetrieveQuotaUsages() throws Exception {
        try (TestAgent agent = newServer()) {
            final QuotasApi quotasApi = agent.getApiClient().getQuotasApi();

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return quotasApi.usage("alpha");
                }
            };

            final QuotaSpec quota = testQuotaSpec()
                    .setName("alpha")
                    .setDescription("Quota Alpha");

            quotasApi.register(quota);

            final ServerQueryResponse<QuotaUsage> quotasResponse = quotasApi.usage("alpha");
            assertUpdatedServerResponse(quotasResponse);

            final QuotaUsage retrievedQuota = quotasResponse.getValue();
            assertThat(retrievedQuota.getName(), is(quota.getName()));
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldBeAbleToDeleteAQuota() throws Exception {
        try (TestAgent agent = newServer()) {
            final QuotasApi quotasApi = agent.getApiClient().getQuotasApi();

            quotasApi.register(testQuotaSpec()
                    .setName("alpha")
                    .setDescription("Quota Alpha"));

            final QuotaSpec quota = quotasApi.info("alpha").getValue();
            assertThat(quota.getDescription(), is("Quota Alpha"));

            final ServerResponse<Void> deleteResponse = quotasApi.delete("alpha");
            assertUpdatedServerResponse(deleteResponse);

            new ErrorResponseAssertion("not found") {
                @Override
                protected NomadResponse<?> performRequest() throws IOException, NomadException {
                    return quotasApi.info("alpha");
                }
            };

        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldListQuotaSpecificationsInReverseCreationOrderAndRespectPrefixes() throws Exception {
        try (TestAgent agent = newServer()) {
            QuotasApi quotasApi = agent.getApiClient().getQuotasApi();

            quotasApi.register(testQuotaSpec().setName("fooaaa"));
            quotasApi.register(testQuotaSpec().setName("foobbb"));

            final ServerQueryResponse<List<QuotaSpec>> quotasResponse = quotasApi.list();
            assertUpdatedServerResponse(quotasResponse);
            final List<QuotaSpec> quotas = quotasResponse.getValue();
            assertThat(quotas, hasSize(2));
            assertThat(quotas.get(0).getName(), is("foobbb"));
            assertThat(quotas.get(1).getName(), is("fooaaa"));

            final List<QuotaSpec> fooPrefixQuotas = quotasApi.list("foo").getValue();
            assertThat(fooPrefixQuotas, hasSize(2));
            assertThat(fooPrefixQuotas.get(0).getName(), is("foobbb"));
            assertThat(fooPrefixQuotas.get(1).getName(), is("fooaaa"));

            final List<QuotaSpec> fooaPrefixQuotas = quotasApi.list("fooa").getValue();
            assertThat(fooaPrefixQuotas, hasSize(1));
            assertThat(fooaPrefixQuotas.get(0).getName(), is("fooaaa"));
        }
    }

    @Test
    @RequiresNomadEnterprise
    public void shouldListQuotaUsagesInReverseCreationOrderAndReusagetPrefixes() throws Exception {
        try (TestAgent agent = newServer()) {
            QuotasApi quotasApi = agent.getApiClient().getQuotasApi();

            quotasApi.register(testQuotaSpec().setName("fooaaa"));
            quotasApi.register(testQuotaSpec().setName("foobbb"));

            final ServerQueryResponse<List<QuotaUsage>> quotasResponse = quotasApi.listUsage();
            assertUpdatedServerResponse(quotasResponse);
            final List<QuotaUsage> quotas = quotasResponse.getValue();
            assertThat(quotas, hasSize(2));
            assertThat(quotas.get(0).getName(), is("foobbb"));
            assertThat(quotas.get(1).getName(), is("fooaaa"));

            final List<QuotaUsage> fooPrefixQuotas = quotasApi.listUsage("foo").getValue();
            assertThat(fooPrefixQuotas, hasSize(2));
            assertThat(fooPrefixQuotas.get(0).getName(), is("foobbb"));
            assertThat(fooPrefixQuotas.get(1).getName(), is("fooaaa"));

            final List<QuotaUsage> fooaPrefixQuotas = quotasApi.listUsage("fooa").getValue();
            assertThat(fooaPrefixQuotas, hasSize(1));
            assertThat(fooaPrefixQuotas.get(0).getName(), is("fooaaa"));
        }
    }

}
