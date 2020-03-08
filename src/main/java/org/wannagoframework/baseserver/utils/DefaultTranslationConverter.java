package org.wannagoframework.baseserver.utils;

import java.util.HashMap;
import java.util.Map;
import org.neo4j.ogm.typeconversion.CompositeAttributeConverter;
import org.wannagoframework.baseserver.domain.graphdb.EntityTranslation;
import org.wannagoframework.baseserver.domain.graphdb.EntityTranslations;

/**
 * @author WannaGo Dev1.
 * @version 1.0
 * @since 10/28/19
 */
public abstract class DefaultTranslationConverter implements CompositeAttributeConverter<EntityTranslations> {
private final String prefix;
  public DefaultTranslationConverter( String prefix ) {
    this.prefix = prefix;
  }
  @Override
  public Map<String, ?> toGraphProperties(EntityTranslations translations) {
    Map<String, String> result = new HashMap<>();
    Map<String, EntityTranslation> entityValue = translations.getTranslations();
    if (entityValue != null) {
      entityValue.keySet().forEach(key -> {
        EntityTranslation t = entityValue.get(key);
        result.put(prefix +"." + key + ".value", t.getValue());
        result.put(prefix +"." + key + ".isTranslated", t.getIsTranslated().toString());
        result.put(prefix +"." + key + ".isDefault", t.getIsDefault().toString());
      });
    }
    return result;
  }

  @Override
  public EntityTranslations toEntityAttribute(Map<String, ?> value) {
    Map<String, EntityTranslation> result = new HashMap<>();
    value.keySet().forEach(key -> {
      String[] vals = key.split("\\.");
      if ( vals.length == 3 ) {
        String iso3Language = vals[1];
        String k = vals[0];
        if ( ! k.equals(prefix))
          return;
        EntityTranslation translation;
        if (result.containsKey(iso3Language)) {
          translation = result.get(iso3Language);
        } else {
          translation = new EntityTranslation();
          translation.setIso3Language(iso3Language);
          result.put(iso3Language, translation);
        }
        if (vals[2].equals("value")) {
          translation.setValue(( String )value.get(key));
        } else if (vals[2].equals("isTranslated")) {
          translation.setIsTranslated(Boolean.parseBoolean((String)value.get(key)));
        } else if (vals[2].equals("isDefault")) {
          translation.setIsDefault(Boolean.parseBoolean((String)value.get(key)));
        }
      }
    });
    return new EntityTranslations(result);
  }
}