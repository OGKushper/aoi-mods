package me.mrdaniel.ageofittgard.util;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

public class TextUtils {

    public static String toString(Text text) {
        return text == null ? null : TextSerializers.FORMATTING_CODE.serialize(text);
    }

    public static Text toText(String text) {
        return text == null ? null : TextSerializers.FORMATTING_CODE.deserialize(text);
    }
}
