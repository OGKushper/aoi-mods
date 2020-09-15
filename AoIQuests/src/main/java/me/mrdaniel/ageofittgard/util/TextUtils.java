package me.mrdaniel.ageofittgard.util;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.List;
import java.util.stream.Collectors;

public class TextUtils {

    public static String toString(Text text) {
        return text == null ? null : TextSerializers.FORMATTING_CODE.serialize(text);
    }

    public static Text toText(String text) {
        return text == null ? null : TextSerializers.FORMATTING_CODE.deserialize(text);
    }

    public static List<Text> deserialize(ConfigurationNode node) throws ObjectMappingException {
        if (node.hasListChildren()) {
            return node.getList(TypeToken.of(String.class)).stream().map(TextUtils::toText).collect(Collectors.toList());
        } else {
            Text obj = toText(node.getString(null));
            return obj == null ? Lists.newArrayList() : Lists.newArrayList(obj);
        }
    }

    public static void serialize(ConfigurationNode node, List<Text> text) throws ObjectMappingException {
        if (text.size() == 1) {
            node.setValue(TextUtils.toString(text.get(0)));
        } else {
            node.setValue(new TypeToken<List<String>>(){}, text.stream().map(TextUtils::toString).collect(Collectors.toList()));
        }
    }
}
