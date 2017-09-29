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
     *
     * @param httpResponse the underlying HTTP response
     * @param rawEntity    the unparsed HTTP response entity (body)
     * @param evaluationId the evaluation ID parsed from the response
     */
    public EvaluationResponse(HttpResponse httpResponse, String rawEntity, String evaluationId) {
        super(httpResponse, rawEntity, evaluationId);
    }

}
