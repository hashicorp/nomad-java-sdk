package com.hashicorp.nomad.javasdk;

import com.hashicorp.nomad.apimodel.Evaluation;
import com.hashicorp.nomad.apimodel.Job;
import com.hashicorp.nomad.apimodel.NodeListStub;
import com.hashicorp.nomad.apimodel.OperatorHealthReply;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Predicates for use with {@link QueryOptions} to poll until a condition is met.
 */
public abstract class NomadPredicates {

    /**
     * Returns a predicate that checks whether the server had a known leader when responding.
     *
     * @param <T> the response value type of the request this predicate will be used with
     */
    public static <T> Predicate<ServerQueryResponse<T>> hadKnownLeader() {
        return new Predicate<ServerQueryResponse<T>>() {
            @Override
            public boolean apply(ServerQueryResponse<T> response) {
                return response.hadKnownLeader();
            }
        };
    }

    /**
     * Returns a predicate that checks whether the cluster was healthy per the autopilot.
     */
    public static Predicate<ServerQueryResponse<OperatorHealthReply>> isHealthy() {
        return new Predicate<ServerQueryResponse<OperatorHealthReply>>() {
            @Override
            public boolean apply(ServerQueryResponse<OperatorHealthReply> response) {
                return response.getValue().getHealthy();
            }
        };
    }

    /**
     * Transforms a predicate on a value of type T into a predicate on a {@code ServerQueryResponse<T>}.
     *
     * @param valuePredicate the predicate for the response value
     * @param <T>            the response value type of the request this predicate will be used with
     */
    public static <T> Predicate<ServerQueryResponse<T>> responseValue(final Predicate<T> valuePredicate) {
        return new Predicate<ServerQueryResponse<T>>() {
            @Override
            public boolean apply(ServerQueryResponse<T> value) {
                return valuePredicate.apply(value.getValue());
            }
        };
    }

    /**
     * Returns a predicate that checks if a list is empty.
     *
     * @param <T> the element type of the list type this predicate will be used with
     */
    public static <T> Predicate<List<T>> nonEmpty() {
        return new Predicate<List<T>>() {
            @Override
            public boolean apply(@Nonnull List<T> collection) {
                return !collection.isEmpty();
            }
        };
    }

    /**
     * Returns a predicate that checks if an evaluation has the given status.
     *
     * @param status the expected status
     */
    public static Predicate<Evaluation> evaluationHasStatus(final String status) {
        return new Predicate<Evaluation>() {
            @Override
            public boolean apply(@Nonnull Evaluation evaluation) {
                return status.equals(evaluation.getStatus());
            }
        };
    }

    /**
     * Returns a predicate that checks if an evaluation has completed.
     */
    public static Predicate<Evaluation> evaluationHasCompleted() {
        return evaluationHasStatus("complete");
    }

    /**
     * Returns a predicate that checks if a job has the given status.
     *
     * @param status the expected status
     */
    public static Predicate<Job> jobHasStatus(final String status) {
        return new Predicate<Job>() {
            @Override
            public boolean apply(Job job) {
                return status.equals(job.getStatus());
            }
        };
    }

    /**
     * Returns a predicate that checks if a job has completed.
     */
    public static Predicate<Job> jobHasCompleted() {
        return jobHasStatus("complete");
    }

    /**
     * Returns a predicate that checks if the given client node is ready.
     *
     * @param name the client's node name
     */
    public static Predicate<List<NodeListStub>> clientNodeIsReady(final String name) {
        return new Predicate<List<NodeListStub>>() {
            @Override
            public boolean apply(List<NodeListStub> nodes) {
                for (NodeListStub node : nodes)
                    if (name.equals(node.getName()))
                        return "ready".equals(node.getStatus());
                return false;
            }
        };
    }

    /**
     * Returns a predicate that is true when both of the given predicates is true.
     *
     * @param a   one of the predicates in the conjunction
     * @param b   one of the predicates in the conjunction
     * @param <T> the type this predicate will be used with
     */
    public static <T> Predicate<T> both(final Predicate<? super T> a, final Predicate<? super T> b) {
        return new Predicate<T>() {
            @Override
            public boolean apply(T value) {
                return a.apply(value) && b.apply(value);
            }
        };
    }
}
