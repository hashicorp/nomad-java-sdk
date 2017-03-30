package com.hashicorp.nomad.javasdk;

import org.apache.http.HttpResponse;

/**
 * Response from a request to a Nomad API that results in a new evaluation.
 *
 * @see <a href="https://www.nomadproject.io/docs/internals/architecture.html#glossary">Nomad Glossary</a> definition of "evaluation"
 */
public class EvaluationResponse extends ServerResponse<String> {

    /**
     * Creates a new EvaluationResponse.
     */
    public EvaluationResponse(HttpResponse httpResponse, String rawEntity, String evaluationId) {
        super(httpResponse, rawEntity, evaluationId);
    }

}
