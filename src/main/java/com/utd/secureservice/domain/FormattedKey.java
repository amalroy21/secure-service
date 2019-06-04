package com.utd.secureservice.domain;

import java.security.KeyPair;

public class FormattedKey {
    public String publicKey;
    public KeyPair pair;

    public FormattedKey(String publicKey, KeyPair pair) {
        this.publicKey = publicKey;
        this.pair = pair;
    }
}
