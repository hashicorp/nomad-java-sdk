package io.github.valfadeev.rundeck_nomad_plugin.nomad;

import java.util.List;
import java.util.ArrayList;
import com.hashicorp.nomad.javasdk.Predicate;

import com.hashicorp.nomad.apimodel.AllocationListStub;
import org.junit.Test;

import static io.github.valfadeev.rundeck_nomad_plugin.nomad.NomadAllocationPredicates.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class NomadAllocationPredicateTest {


    private static Predicate<AllocationListStub> allocationHasClientDescription(final String description) {
        return allocationListStub -> description.equals(allocationListStub.getClientDescription());
    }

    private static Predicate<AllocationListStub> allocationHasTestDescription() {
        return allocationHasClientDescription("test");
    }

    @Test
    public void shouldEvaluatePredicateChecks() throws Exception {

      AllocationListStub pending = new AllocationListStub()
              .setClientStatus("pending");
      AllocationListStub running = new AllocationListStub()
              .setClientStatus("running");
      AllocationListStub failed1 = new AllocationListStub()
              .setClientStatus("failed");
      AllocationListStub failed2 = new AllocationListStub()
              .setClientStatus("failed");
      AllocationListStub lost = new AllocationListStub()
              .setClientStatus("lost");
      AllocationListStub complete = new AllocationListStub()
              .setClientStatus("complete")
              .setClientDescription("test");

      List<AllocationListStub> finished = new ArrayList<>();
      finished.add(failed1);
      finished.add(lost);
      finished.add(complete);

      List<AllocationListStub> notFinished = new ArrayList<>();
      notFinished.add(pending);
      notFinished.add(running);
      notFinished.add(complete);
      notFinished.add(failed2);

      List<AllocationListStub> failedOver = new ArrayList<>();
      failedOver.add(failed1);
      failedOver.add(complete);
      failedOver.add(failed2);

      List<Predicate<AllocationListStub>> finishedP = new ArrayList<>();
      finishedP.add(allocationHasBeenLost());
      finishedP.add(allocationHasCompleted());
      finishedP.add(allocationHasFailed());

      List<Predicate<AllocationListStub>> unfinishedP = new ArrayList<>();
      unfinishedP.add(allocationIsPending());
      unfinishedP.add(allocationIsRunning());

      List<Predicate<AllocationListStub>> testP = new ArrayList<>();
      testP.add(allocationHasCompleted());
      testP.add(allocationHasTestDescription());
      Predicate<AllocationListStub> allTestP = all(testP);

      Predicate<AllocationListStub> anyFinishedP = any(finishedP);
      Predicate<AllocationListStub> anyUnfinishedP = any(unfinishedP);
      Predicate<AllocationListStub> notAnyUnFinishedP = not(anyUnfinishedP);


      assertThat(allocationHasBeenLost().apply(lost), is(true));
      assertThat(allocationHasCompleted().apply(complete), is(true));
      assertThat(allocationHasFailed().apply(failed2), is(true));

      assertThat(allocationFinishedRunning().apply(failed1), is(true));
      assertThat(allocationFinishedRunning().apply(lost), is(true));
      assertThat(allocationFinishedRunning().apply(complete), is(true));
      assertThat(allocationFinishedRunning().apply(pending), is(false));
      assertThat(allocationFinishedRunning().apply(running), is(false));

      assertThat(anyUnfinishedP.apply(pending), is(true));
      assertThat(anyUnfinishedP.apply(running), is(true));

      assertThat(anyFinishedP.apply(complete), is(true));
      assertThat(anyFinishedP.apply(complete), is(true));
      assertThat(anyFinishedP.apply(complete), is(true));

      assertThat(allTestP.apply(complete), is(true));

      assertThat(notAnyUnFinishedP.apply(complete), is(true));
      assertThat(notAnyUnFinishedP.apply(failed1), is(true));
      assertThat(notAnyUnFinishedP.apply(lost), is(true));

      assertThat(allAllocationsFinished().apply(finished), is(true));
      assertThat(allAllocationsFinished().apply(notFinished), is(false));

      assertThat(failedAllocationsOver(50L).apply(failedOver), is(true));

    }
}

