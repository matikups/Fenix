package com.mapbox.rctmgl.components.styles;

import android.support.annotation.NonNull;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.gson.JsonArray;
import com.mapbox.mapboxsdk.style.expressions.Expression;
import com.mapbox.mapboxsdk.style.layers.TransitionOptions;
import com.mapbox.rctmgl.utils.ConvertUtils;
import com.mapbox.rctmgl.utils.ExpressionParser;

/**
 * Created by nickitaliano on 9/12/17.
 */

public class RCTMGLStyleValue {

    private String mType;
    private boolean isExpression;
    private Expression mExpression;
    private ReadableMap mPayload;

    private String imageURI = "";
    private boolean isAddImage;
    private Double imageScale = 1.0;

    public static final int InterpolationModeExponential = 100;
    public static final int InterpolationModeInterval = 101;
    public static final int InterpolationModeCategorical = 102;
    public static final int InterpolationModeIdentity = 103;

    public RCTMGLStyleValue(@NonNull ReadableMap config) {
        mType = config.getString("styletype");
        mPayload = config.getMap("stylevalue");

        if ("image".equals(mType)) {
            imageScale = 1.0;
            if ("hashmap".equals(mPayload.getString("type"))) {
                ReadableMap map = getMap();
                imageURI = map.getMap("uri").getString("value");
                if (map.getMap("scale") != null) {
                    imageScale = map.getMap("scale").getDouble("value");
                }
            } else {
                imageURI = mPayload.getString("value");
            }
            isAddImage = imageURI != null && imageURI.contains("://");
            return;
        }

        Dynamic dynamic = mPayload.getDynamic("value");
        if (dynamic.getType().equals(ReadableType.Array)) {
            ReadableArray array = dynamic.asArray();
            if (array.size() > 0 && mPayload.getString("type").equals("array")) {
                ReadableMap map = array.getMap(0);
                if (map != null && map.getString("type").equals("string")) {
                    isExpression = true;
                    mExpression = ExpressionParser.fromTyped(mPayload);
                }
            }
        }
    }

    public String getType() {
        return mType;
    }

    public boolean isFunction() {
        return mType.equals("function");
    }

    public int getInt(String key) {
        return mPayload.getInt(key);
    }

    public String getString(String key) {
        return mPayload.getString(key);
    }

    public Double getDouble(String key) {
        return mPayload.getDouble(key);
    }

    public Float getFloat(String key) {
        return getDouble(key).floatValue();
    }

    public Dynamic getDynamic(String key) {
        return mPayload.getDynamic(key);
    }

    public ReadableArray getArray(String key) {
        return mPayload.getArray(key);
    }

    public Boolean getBoolean(String key) {
        return mPayload.getBoolean(key);
    }

    public Float[] getFloatArray(String key) {
        ReadableArray arr = getArray(key);

        Float[] floatArr = new Float[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            ReadableMap item = arr.getMap(i);
            floatArr[i] = (float) item.getDouble("value");
        }

        return floatArr;
    }

    public String[] getStringArray(String key) {
        ReadableArray arr = getArray(key);

        String[] stringArr = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            ReadableMap item = arr.getMap(i);
            stringArr[i] = item.getString("value");
        }

        return stringArr;
    }

    public ReadableMap getMap() {
        if ("hashmap".equals(mPayload.getString("type"))) {
            ReadableArray keyValues = mPayload.getArray("value");
            WritableNativeMap result = new WritableNativeMap();
            for (int i = 0; i < keyValues.size(); i++) {
                ReadableArray keyValue = keyValues.getArray(i);
                String stringKey = keyValue.getMap(0).getString("value");
                WritableNativeMap value = new WritableNativeMap();
                value.merge(keyValue.getMap(1));
                result.putMap(stringKey, value);
            }
            return result;
        }

        return null;
    }

    public ReadableMap getMap(String _key) {
        return getMap();
    }

    public Expression getExpression() {
        return mExpression;
    }

    public boolean isExpression() {
        return isExpression;
    }

    public boolean shouldAddImage() {
        return isAddImage;
    }

    public String getImageURI() {
        return imageURI;
    }

    public double getImageScale() {
        return imageScale;
    }

    public TransitionOptions getTransition() {
        if (!mType.equals("transition")) {
            return null;
        }
        ReadableMap config = getMap(RCTMGLStyleFactory.VALUE_KEY);

        boolean enablePlacementTransitions = true;
        if (config.hasKey("enablePlacementTransitions")) {
            enablePlacementTransitions = config.getMap("enablePlacementTransitions").getBoolean("value");
        }
        return new TransitionOptions(config.getMap("duration").getInt("value"), config.getMap("delay").getInt("value"), enablePlacementTransitions);
    }
}
