package com.hashicorp.nomad.javasdk;

import org.apache.http.conn.ssl.DefaultHostnameVerifier;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A hostname verifier that accepts certificates of the form expected from Nomad,
 * i.e. with alternative names of the form {@code server.*.nomad} or {@code server.*.nomad},
 * in addition to the usual HTTPS behaviour of accepting alt names matching the host being connected to.
 */
class NomadHostnameVerifier implements HostnameVerifier {

    private static final Pattern NOMAD_ALT_NAME_PATTERN =
            Pattern.compile("^(?:server|client)\\..+\\.nomad$");

    private final DefaultHostnameVerifier defaultHostnameVerifier = new DefaultHostnameVerifier();

    @Override
    public boolean verify(String host, SSLSession sslSession) {
        return hasNomadAgentAltName(sslSession) || defaultHostnameVerifier.verify(host, sslSession);
    }

    private boolean hasNomadAgentAltName(final SSLSession session) {
        try {
            final X509Certificate certificate = (X509Certificate) session.getPeerCertificates()[0];
            final Collection<List<?>> entries = certificate.getSubjectAlternativeNames();
            if (entries == null) {
                return false;
            }
            for (List<?> entry : entries) {
                if (entry.size() >= 2 && (Integer) entry.get(0) == 2) {
                    final String name = (String) entry.get(1);
                    if (NOMAD_ALT_NAME_PATTERN.matcher(name).matches()) {
                        return true;
                    }
                }
            }
        } catch (final SSLException | CertificateParsingException ignored) {
        }
        return false;
    }
}
