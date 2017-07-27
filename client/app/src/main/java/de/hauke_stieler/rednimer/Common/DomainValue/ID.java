package de.hauke_stieler.rednimer.Common.DomainValue;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Random;

import juard.contract.Contract;

/**
 * Created by hauke on 27.07.17.
 */

public class ID<T> {
    public static final String LOG_TAG = "ID<>";
    private final String _value;

    private final Class<T> _type;

    private ID(Class<T> type, String value) {
        Contract.NotNull(type);
        Contract.NotNullOrEmpty(value);

        _value = value;
        _type = type;
    }

    public static <T> ID<T> create(Class<T> type) {
        Contract.NotNull(type);

        String hash = generateUniqueId();

        Log.i(LOG_TAG, MessageFormat.format("create: New ID is ''{0}''.", hash));

        return create(type, hash);
    }

    public static <T> ID<T> create(Class<T> type, String value) {
        Contract.NotNull(type);
        Contract.NotNullOrEmpty(value);

        return new ID(type, value);
    }

    private static String generateUniqueId() {
        try {
            Random random = new Random();

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Generate a string with rather random values:
            StringBuilder stringBuilder = new StringBuilder("");
            stringBuilder.append(System.nanoTime());
            stringBuilder.append(random.nextDouble());
            stringBuilder.append(random.nextGaussian());
            stringBuilder.append(random.nextLong());

            random = new Random();
            stringBuilder.append(random.nextDouble()*random.nextFloat());
            stringBuilder.append(random.nextGaussian()+random.nextLong());

            String text = stringBuilder.toString();

            // Generate SHA-256 value
            md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();

            // Create hex-String
            String hash = String.format("%064x", new BigInteger(1, digest));

            return hash;

        } catch (NoSuchAlgorithmException e) {
            Log.e(LOG_TAG, "generateUniqueId: Algorithm not found!", e);

            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "generateUniqueId: Encoding not supported!", e);

            throw new RuntimeException(e);
        }
    }

    public String getValue() {
        return _value;
    }

    public Class<T> getType() {
        return _type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ID)) {
            return false;
        }

        ID other = (ID) o;

        return getValue().equals(other.getValue()) &&
                getType().equals(other.getType());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    @Override
    public String toString() {
        return MessageFormat.format("'{'ID: {0}'}'", getValue());
    }
}
