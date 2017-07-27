package de.hauke_stieler.rednimer.Common.DomainValue;

import java.text.MessageFormat;

import juard.contract.Contract;

/**
 * Created by hauke on 27.07.17.
 */

public class ID {
    private final String _value;

    private ID(String value){
        Contract.NotNullOrEmpty(value);

        _value = value;
    }

    public static ID create(){
        //TODO create new SHA-256 ID
        throw new UnsupportedOperationException();
    }

    public static ID create(String value){
        Contract.NotNullOrEmpty(value);

        return new ID(value);
    }

    public String getValue() {
        return _value;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof ID)){
            return false;
        }

        return getValue().equals(((ID) o).getValue());
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
