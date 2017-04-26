package com.hashicorp.nomad.javasdk;

import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * Utilities for loading PEM files into the types expected by {@link javax.net.ssl.SSLContext}.
 */
final class TlsUtils {

    private TlsUtils() { }

    static TrustManager[] trustPemCertificate(final String path) throws GeneralSecurityException, IOException {
        return asTrustManagers(asKeyStore(loadPemCertificate(path)));
    }

    static KeyManager[] usePemCertificateAndKey(final String certPath, final String keyPath)
            throws GeneralSecurityException, IOException {
        return asKeyManagers(asKeyStore(loadPemCertificate(certPath), loadPemKey(keyPath)));
    }

    private static X509Certificate loadPemCertificate(final String path) throws CertificateException, IOException {
        final CertificateFactory factory = CertificateFactory.getInstance("X.509");
        try (FileInputStream is = new FileInputStream(path)) {
            return (X509Certificate) factory.generateCertificate(is);
        }
    }

    private static PrivateKey loadPemKey(final String path) throws IOException {
        try (final FileInputStream in = new FileInputStream(path)) {
            try (final InputStreamReader reader = new InputStreamReader(in, US_ASCII)) {
                try (PEMParser parser = new PEMParser(reader)) {
                    return new JcaPEMKeyConverter().getKeyPair((PEMKeyPair) parser.readObject()).getPrivate();
                }
            }
        }
    }

    private static KeyStore asKeyStore(final Certificate certificate) throws GeneralSecurityException, IOException {
        final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        keyStore.setCertificateEntry("certificate", certificate);
        return keyStore;
    }

    private static KeyStore asKeyStore(final Certificate certificate, final PrivateKey key)
            throws GeneralSecurityException, IOException {
        final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        keyStore.setKeyEntry("key", key, new char[]{}, new Certificate[]{certificate});
        return keyStore;
    }

    private static TrustManager[] asTrustManagers(final KeyStore keyStore) throws GeneralSecurityException {
        final TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        factory.init(keyStore);
        return factory.getTrustManagers();
    }

    private static KeyManager[] asKeyManagers(final KeyStore keyStore) throws GeneralSecurityException {
        final KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        factory.init(keyStore, new char[]{});
        return factory.getKeyManagers();
    }
}
