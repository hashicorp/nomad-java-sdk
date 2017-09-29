package com.hashicorp.nomad.javasdk;

import java.util.List;

import com.hashicorp.nomad.apimodel.AllocationListStub;
import org.junit.Test;

import static com.hashicorp.nomad.javasdk.NomadPredicates.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class NomadPredicatesTest {

    @Test
    public void shouldEvaluatePredicateChecks() throws Exception {

        AllocationListStub pending = new AllocationListStub().setClientStatus("pending");
        AllocationListStub running = new AllocationListStub().setClientStatus("running");
        AllocationListStub failed = new AllocationListStub().setClientStatus("failed");
        AllocationListStub lost = new AllocationListStub().setClientStatus("lost");
        AllocationListStub complete = new AllocationListStub()
                .setClientStatus("complete")
                .setClientDescription("test");

        List<AllocationListStub> finished = asList(failed, lost, complete);
        List<AllocationListStub> notFinished = asList(pending, running, complete, failed);

        final Predicate<AllocationListStub> allocationHasTestDescription =
                new Predicate<AllocationListStub>() {
                    @Override
                    public boolean apply(AllocationListStub value) {
                        return "test".equals(value.getClientDescription());
                    }
                };

        Predicate<AllocationListStub> allTestP = allOf(asList(
                allocationHasCompleted(),
                allocationHasTestDescription
        ));

        Predicate<AllocationListStub> anyFinishedP = anyOf(asList(
                allocationHasCompleted(),
                allocationHasFailed()
        ));

        assertThat(allocationHasCompleted().apply(complete), is(true));
        assertThat(allocationHasFailed().apply(failed), is(true));

        assertThat(allocationFinishedRunning().apply(failed), is(true));
        assertThat(allocationFinishedRunning().apply(lost), is(true));
        assertThat(allocationFinishedRunning().apply(complete), is(true));
        assertThat(allocationFinishedRunning().apply(pending), is(false));
        assertThat(allocationFinishedRunning().apply(running), is(false));

        assertThat(anyFinishedP.apply(complete), is(true));
        assertThat(anyFinishedP.apply(complete), is(true));
        assertThat(anyFinishedP.apply(complete), is(true));

        assertThat(allTestP.apply(complete), is(true));

        assertThat(allAllocationsFinished().apply(finished), is(true));
        assertThat(allAllocationsFinished().apply(notFinished), is(false));
    }
}

