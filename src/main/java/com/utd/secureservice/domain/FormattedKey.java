package com.utd.secureservice.domain;

import java.security.KeyPair;
import java.security.PrivateKey;

public class FormattedKey {
    public String publicKey;
    public KeyPair pair;

    public FormattedKey(String publicKey, KeyPair pair) {
        this.publicKey = publicKey;
        this.pair = pair;
    }
}
