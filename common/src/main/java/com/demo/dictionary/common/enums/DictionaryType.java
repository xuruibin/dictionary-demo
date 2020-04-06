package com.demo.dictionary.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @author Dora B
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DictionaryType {

    /**
     * dictionary type
     */
    system(1),
    custom(2);

    private int code;

    public static final HashMap<Integer, DictionaryType> MAP = new HashMap<>();

    static {
        for (DictionaryType enums : DictionaryType.values()) {
            if (MAP.put(enums.getCode(), enums) != null) {
                throw new IllegalArgumentException("duplicate enums: " + enums.name());
            }
        }
    }
}
