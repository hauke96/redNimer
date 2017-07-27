package de.hauke_stieler.rednimer.Common.DomainValue;

import java.text.MessageFormat;

import juard.contract.Contract;

/**
 * Created by hauke on 27.07.17.
 */

public class ID<T> {
    private final String _value;

    private final Class<T> _type;

    private ID(Class<T> type, String value){
        Contract.NotNull(type);
        Contract.NotNullOrEmpty(value);

        _value = value;
        _type = type;
    }

    public static <T> ID<T> create(Class<T> type){
        //TODO create new SHA-256 ID
        throw new UnsupportedOperationException();
    }

    public static <T> ID<T> create(Class<T> type, String value){
        Contract.NotNull(type);
        Contract.NotNullOrEmpty(value);

        return new ID(type, value);
    }

    public String getValue() {
        return _value;
    }

    public Class<T> getType(){
        return _type;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof ID)){
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
