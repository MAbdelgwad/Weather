package cz.eng.weather.models;



import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(AndroidJUnit4.class)
@Config(sdk = 27)
public class ImmutableWeatherTests {
    @Test
    public void constructorParsesTemperature() {
        String jsonWithTemp = "{\"main\": {\"temp\": 315.25}}";
        String jsonWithoutTemp = "{\"main\": {}}";
        String jsonWithoutMain = "{\"wind\": {}}";
        String jsonWithNonParsableTemp = "{\"main\": {\"temp\": \"parseThis\"}}";

        ImmutableWeather weatherWithTemp = ImmutableWeather.fromJson(jsonWithTemp, -1);
        ImmutableWeather weatherWithoutTemp = ImmutableWeather.fromJson(jsonWithoutTemp, -1);
        ImmutableWeather weatherWithNonParcelableTemp =
                ImmutableWeather.fromJson(jsonWithNonParsableTemp, -1);
        ImmutableWeather weatherWithoutMain = ImmutableWeather.fromJson(jsonWithoutMain, -1);

        Assert.assertEquals("temperature is wrong", 315.25f,
                weatherWithTemp.getTemperature(), 0.01f);
        Assert.assertEquals("default temperature is wrong", Float.MIN_VALUE,
                weatherWithoutTemp.getTemperature(), Float.MIN_VALUE);
        Assert.assertEquals("default temperature for non-parcelable temperature is wrong",
                Float.MIN_VALUE, weatherWithNonParcelableTemp.getTemperature(), Float.MIN_VALUE);
        Assert.assertEquals("default temperature without main object is wrong",
                Float.MIN_VALUE, weatherWithoutMain.getTemperature(), Float.MIN_VALUE);
    }
    @Test
    public void constructorParsesPressure() {
        String jsonWithPressure = "{\"main\": {\"pressure\": 1016}}";
        String jsonWithoutPressure = "{\"main\": {}}";
        String jsonWithNonParsablePressure = "{\"main\": {\"pressure\": \"parseThis\"}}";

        ImmutableWeather weatherWithPressure = ImmutableWeather.fromJson(jsonWithPressure, -1);
        ImmutableWeather weatherWithoutPressure = ImmutableWeather.fromJson(jsonWithoutPressure, -1);
        ImmutableWeather weatherWithNonParcelablePressure = ImmutableWeather.fromJson(
                jsonWithNonParsablePressure, -1);

        Assert.assertEquals("pressure is wrong", 1016.0,
                weatherWithPressure.getPressure(), 0.1);
        Assert.assertEquals("default pressure is wrong", Double.MIN_VALUE,
                weatherWithoutPressure.getPressure(), Double.MIN_VALUE);
        Assert.assertEquals("default pressure for non-parcelable pressure is wrong",
                Double.MIN_VALUE, weatherWithNonParcelablePressure.getPressure(), Double.MIN_VALUE);
    }
    @Test
    public void constructorParsesHumidity() {
        String jsonWithHumidity = "{\"main\": {\"humidity\": 100}}";
        String jsonWithoutHumidity = "{\"main\": {}}";
        String jsonWithNonParsableHumidity = "{\"main\": {\"humidity\": \"parseThis\"}}";

        ImmutableWeather weatherWithHumidity = ImmutableWeather.fromJson(jsonWithHumidity, -1);
        ImmutableWeather weatherWithoutHumidity = ImmutableWeather.fromJson(jsonWithoutHumidity, -1);
        ImmutableWeather weatherWithNonParcelableHumidity = ImmutableWeather.fromJson(
                jsonWithNonParsableHumidity, -1);

        Assert.assertEquals("humidity is wrong", 100,
                weatherWithHumidity.getHumidity());
        Assert.assertEquals("default humidity is wrong", -1,
                weatherWithoutHumidity.getHumidity());
        Assert.assertEquals("default humidity for non-parcelable humidity is wrong",
                -1, weatherWithNonParcelableHumidity.getHumidity());
    }
    @Test
    public void constructorParsesWindSpeed() {
        String jsonWithWindSpeed = "{\"wind\": {\"speed\": 1.5}}";
        String jsonWithoutWindSpeed = "{\"wind\": {}}";
        String jsonWithNonParsableWindSpeed = "{\"wind\": {\"speed\": \"parseThis\"}}";
        String jsonWithoutWind = "{\"main\": {}}";

        ImmutableWeather weatherWithWindSpeed = ImmutableWeather.fromJson(jsonWithWindSpeed, -1);
        ImmutableWeather weatherWithoutWindSpeed = ImmutableWeather.fromJson(jsonWithoutWindSpeed, -1);
        ImmutableWeather weatherWithNonParcelableWindSpeed = ImmutableWeather.fromJson(
                jsonWithNonParsableWindSpeed, -1);
        ImmutableWeather weatherWithoutWind = ImmutableWeather.fromJson(jsonWithoutWind, -1);

        Assert.assertEquals("wind speed is wrong", 1.5,
                weatherWithWindSpeed.getWindSpeed(), 0.1);
        Assert.assertEquals("default wind speed is wrong", Double.MIN_VALUE,
                weatherWithoutWindSpeed.getWindSpeed(), Double.MIN_VALUE);
        Assert.assertEquals("default wind speed for non-parcelable wind speed is wrong",
                Double.MIN_VALUE, weatherWithNonParcelableWindSpeed.getWindSpeed(), Double.MIN_VALUE);
        Assert.assertEquals("default wind speed without wind object is wrong",
                Double.MIN_VALUE, weatherWithoutWind.getWindSpeed(), Double.MIN_VALUE);
    }
    @Test
    public void constructorParsesWindDirection() {
        String jsonWithWindDirection = "{\"wind\": {\"deg\": 350}}";
        String jsonWithoutWindDirection = "{\"wind\": {}}";
        String jsonWithNonParsableWindDirection = "{\"wind\": {\"deg\": \"parseThis\"}}";
        String jsonWithoutWind = "{\"main\": {}}";

        ImmutableWeather weatherWithWindDirection =
                ImmutableWeather.fromJson(jsonWithWindDirection, -1);
        ImmutableWeather weatherWithoutWindDirection =
                ImmutableWeather.fromJson(jsonWithoutWindDirection, -1);
        ImmutableWeather weatherWithNonParcelableWindDirection =
                ImmutableWeather.fromJson( jsonWithNonParsableWindDirection, -1);
        ImmutableWeather weatherWithoutWind =
                ImmutableWeather.fromJson(jsonWithoutWind, -1);

        Assert.assertEquals("wind direction is wrong",
                Weather.WindDirection.NORTH, weatherWithWindDirection.getWindDirection());
        Weather.WindDirection directionWithoutDirectionField =
                weatherWithoutWindDirection.getWindDirection();
        Assert.assertNull(
                "default wind direction should be null" +
                        " but it is " + directionWithoutDirectionField,
                directionWithoutDirectionField);
        Weather.WindDirection nonParcelableDirection =
                weatherWithNonParcelableWindDirection.getWindDirection();
        Assert.assertNull(
                "default wind direction for non-parcelable wind direction should be null" +
                        " but it is " + nonParcelableDirection,
                nonParcelableDirection);
        Weather.WindDirection directionWithoutWindObject = weatherWithoutWind.getWindDirection();
        Assert.assertNull(
                "default wind direction without wind object should be null" +
                        " but it is " + directionWithoutWindObject,
                directionWithoutWindObject);
    }
    @Test
    public void constructorParsesDescription() {
        String expectedDescription = "clear sky";
        String jsonWithDescription = String.format("{\"weather\": [{\"description\": \"%s\"}]}",
                expectedDescription);
        String jsonWithoutDescription = "{\"weather\": []}";
        String jsonWithoutWeather = "{\"main\": {}}";

        ImmutableWeather weatherWithDescription =
                ImmutableWeather.fromJson(jsonWithDescription, -1);
        ImmutableWeather weatherWithoutDescription =
                ImmutableWeather.fromJson(jsonWithoutDescription, -1);
        ImmutableWeather weatherWithoutWeatherObject =
                ImmutableWeather.fromJson(jsonWithoutWeather, -1);

        Assert.assertEquals("description is wrong", expectedDescription,
                weatherWithDescription.getDescription());
        Assert.assertEquals("default description is wrong", "",
                weatherWithoutDescription.getDescription());
        Assert.assertEquals("default description without weather object is wrong",
                "", weatherWithoutWeatherObject.getDescription());
    }
    @Test
    public void constructorParsesCity() {
        String expectedCity = "Mountain View";
        String jsonWithCity = String.format("{\"name\": \"%s\"}",
                expectedCity);
        String jsonWithoutCity = "{\"weather\": {}}";

        ImmutableWeather weatherWithCity = ImmutableWeather.fromJson(jsonWithCity, -1);
        ImmutableWeather weatherWithoutCity =
                ImmutableWeather.fromJson(jsonWithoutCity, -1);

        Assert.assertEquals("city name is wrong", expectedCity,
                weatherWithCity.getCity());
        Assert.assertEquals("default description is wrong", "",
                weatherWithoutCity.getDescription());
    }
    @Test
    public void constructorParsesCountry() {
        String expectedCountry = "US";
        String jsonWithCountry = String.format("{\"sys\": {\"country\": \"%s\"}}",
                expectedCountry);
        String jsonWithoutCountry = "{\"sys\": {}}";
        String jsonWithoutSys = "{\"main\": {}}";

        ImmutableWeather weatherWithCountry =
                ImmutableWeather.fromJson(jsonWithCountry, -1);
        ImmutableWeather weatherWithoutCountry =
                ImmutableWeather.fromJson(jsonWithoutCountry, -1);
        ImmutableWeather weatherWithoutSysObject =
                ImmutableWeather.fromJson(jsonWithoutSys, -1);

        Assert.assertEquals("country is wrong", expectedCountry,
                weatherWithCountry.getCountry());
        Assert.assertEquals("default country is wrong", "",
                weatherWithoutCountry.getCountry());
        Assert.assertEquals("default country without sys object is wrong",
                "", weatherWithoutSysObject.getCountry());
    }
    @Test
    public void constructorParsesSunrise() {
        long expectedSunrise = 1560343627;
        String jsonWithSunrise = String.format("{\"sys\": {\"sunrise\": %d}}", expectedSunrise);
        String jsonWithoutSunrise = "{\"sys\": {}}";
        String jsonWithoutSysObject = "{\"main\": {}}";
        String jsonWithNonParcelableSunrise = "{\"sys\": {\"sunrise\": \"parseThis\"}}";
        String jsonWithNegativeSunrise = String.format("{\"sys\": {\"sunrise\": %d}}", -2L);

        ImmutableWeather weatherWithSunrise =
                ImmutableWeather.fromJson(jsonWithSunrise, -1);
        ImmutableWeather weatherWithoutSunrise =
                ImmutableWeather.fromJson(jsonWithoutSunrise, -1);
        ImmutableWeather weatherWithNonParsableSunrise =
                ImmutableWeather.fromJson(jsonWithNonParcelableSunrise, -1);
        ImmutableWeather weatherWithoutSysObject =
                ImmutableWeather.fromJson(jsonWithoutSysObject, -1);
        ImmutableWeather weatherWithNegativeSunrise =
                ImmutableWeather.fromJson(jsonWithNegativeSunrise, -1);

        Assert.assertEquals("sunrise is wrong", expectedSunrise,
                weatherWithSunrise.getSunrise());
        Assert.assertEquals("default sunrise is wrong", -1L,
                weatherWithoutSunrise.getSunrise());
        Assert.assertEquals("default sunrise for non-parcelable sunrise is wrong",
                -1L, weatherWithNonParsableSunrise.getSunrise());
        Assert.assertEquals("default sunrise without sys object is wrong", -1L,
                weatherWithoutSysObject.getSunrise());
        Assert.assertEquals("default sunrise when sunrise is negative is wrong",
                -1L, weatherWithNegativeSunrise.getSunrise());
    }
    @Test
    public void constructorParsesSunset() {
        long expectedSunset = 1560396563;
        String jsonWithSunset = String.format("{\"sys\": {\"sunset\": %d}}", expectedSunset);
        String jsonWithoutSunset = "{\"sys\": {}}";
        String jsonWithoutSysObject = "{\"main\": {}}";
        String jsonWithNonParcelableSunset = "{\"sys\": {\"sunset\": \"parseThis\"}}";
        String jsonWithNegativeSunset = String.format("{\"sys\": {\"sunset\": %d}}", -2L);

        ImmutableWeather weatherWithSunset =
                ImmutableWeather.fromJson(jsonWithSunset, -1);
        ImmutableWeather weatherWithoutSunset =
                ImmutableWeather.fromJson(jsonWithoutSunset, -1);
        ImmutableWeather weatherWithNonParsableSunset =
                ImmutableWeather.fromJson(jsonWithNonParcelableSunset, -1);
        ImmutableWeather weatherWithoutSysObject =
                ImmutableWeather.fromJson(jsonWithoutSysObject, -1);
        ImmutableWeather weatherWithNegativeSunset =
                ImmutableWeather.fromJson(jsonWithNegativeSunset, -1);

        Assert.assertEquals("sunset is wrong", expectedSunset,
                weatherWithSunset.getSunset());
        Assert.assertEquals("default sunset is wrong", -1L,
                weatherWithoutSunset.getSunset());
        Assert.assertEquals("default sunset for non-parcelable sunset is wrong",
                -1L, weatherWithNonParsableSunset.getSunset());
        Assert.assertEquals("default sunset without sys object is wrong", -1L,
                weatherWithoutSysObject.getSunset());
        Assert.assertEquals("default sunset when sunset is negative is wrong",
                -1L, weatherWithNegativeSunset.getSunset());
    }

    @Test
    public void getTemperatureConvertsTemperatureIntoSpecifiedUnit() {
        String jsonWithTemp = "{\"main\": {\"temp\": 315.25}}";
        String jsonWithoutTemp = "{\"main\": {}}";

        ImmutableWeather weatherWithTemp = ImmutableWeather.fromJson(jsonWithTemp, -1);
        ImmutableWeather weatherWithoutTemp = ImmutableWeather.fromJson(jsonWithoutTemp, -1);

        Assert.assertEquals("temperature in kelvins is wrong", 315.25f,
                weatherWithTemp.getTemperature("K"), 0.01f);
        Assert.assertEquals("temperature in celsius is wrong", 42.1f,
                weatherWithTemp.getTemperature("°C"), 0.01f);
        Assert.assertEquals("temperature in fahrenheit is wrong", 107.78f,
                weatherWithTemp.getTemperature("°F"), 0.01f);
        Assert.assertEquals("default temperature should not be converted", Float.MIN_VALUE,
                weatherWithoutTemp.getTemperature("°C"), Float.MIN_VALUE);
    }

    @Test
    public void getRoundedTemperatureConvertsTemperatureIntoSpecifiedUnitAndRounds() {
        String jsonWithTemp = "{\"main\": {\"temp\": 315.25}}";
        String jsonWithoutTemp = "{\"main\": {}}";

        ImmutableWeather weatherWithTemp = ImmutableWeather.fromJson(jsonWithTemp, -1);
        ImmutableWeather weatherWithoutTemp = ImmutableWeather.fromJson(jsonWithoutTemp, -1);

        Assert.assertEquals("rounded temperature in kelvins is wrong", 315,
                weatherWithTemp.getRoundedTemperature("K"));
        Assert.assertEquals("rounded temperature in celsuis is wrong", 42,
                weatherWithTemp.getRoundedTemperature("°C"));
        Assert.assertEquals("rounded temperature in fahrenheit is wrong", 108,
                weatherWithTemp.getRoundedTemperature("°F"));
        Assert.assertEquals("default temperature should not be converted or rounded",
                Integer.MIN_VALUE, weatherWithoutTemp.getRoundedTemperature("°C"));
    }

    @Test
    public void getPressureConvertsPressureIntoSpecifiedUnit() {
        String jsonWithPressure = "{\"main\": {\"pressure\": 1016}}";
        String jsonWithoutPressure = "{\"main\": {}}";

        ImmutableWeather weatherWithPressure = ImmutableWeather.fromJson(jsonWithPressure, -1);
        ImmutableWeather weatherWithoutPressure = ImmutableWeather.fromJson(jsonWithoutPressure, -1);

        Assert.assertEquals("pressure in hPa/mBar (\"hPa\") is wrong", 1016.0,
                weatherWithPressure.getPressure("hPa"), 0.1);
        Assert.assertEquals("pressure in hPa/mBar (\"hPa/mBar\") is wrong", 1016.0,
                weatherWithPressure.getPressure("hPa/mBar"), 0.1);
        Assert.assertEquals("pressure in kPa is wrong", 101.6,
                weatherWithPressure.getPressure("kPa"), 0.1);
        Assert.assertEquals("pressure in mm Hg is wrong", 1016.0 * 0.750061561303,
                weatherWithPressure.getPressure("mm Hg"), 0.1);
        Assert.assertEquals("pressure in in Hg is wrong", 1016.0 * 0.0295299830714,
                weatherWithPressure.getPressure("in Hg"), 0.1);
        Assert.assertEquals("default pressure should not be converted",
                Double.MIN_VALUE, weatherWithoutPressure.getPressure("kPa"), Double.MIN_VALUE);
    }

    @Test
    public void getWindSpeedConvertsPressureIntoSpecifiedUnit() {
        String jsonWithWindSpeed = "{\"wind\": {\"speed\": 1.5}}";
        String jsonWithoutWindSpeed = "{\"wind\": {}}";

        ImmutableWeather weatherWithWindSpeed = ImmutableWeather.fromJson(jsonWithWindSpeed, -1);
        ImmutableWeather weatherWithoutWindSpeed = ImmutableWeather.fromJson(jsonWithoutWindSpeed, -1);

        Assert.assertEquals("wind speed in meters per second (m/s) is wrong", 1.5,
                weatherWithWindSpeed.getWindSpeed("m/s"), 0.1);
        Assert.assertEquals("wind speed in kilometers per hour (kph) is wrong",
                1.5 * 3.6, weatherWithWindSpeed.getWindSpeed("kph"), 0.1);
        Assert.assertEquals("wind speed in miles per hour (mph) is wrong",
                1.5 * 2.23693629205, weatherWithWindSpeed.getWindSpeed("mph"),
                0.1);
        Assert.assertEquals("wind speed in knots (kn) is wrong",
                1.5 * 1.943844, weatherWithWindSpeed.getWindSpeed("kn"),
                0.1);
        Assert.assertEquals("wind speed in Beaufort wind scale (bft) is wrong",
                2, weatherWithWindSpeed.getWindSpeed("bft"), 0.1);
        Assert.assertEquals("default wind speed should not be converted",
                Double.MIN_VALUE, weatherWithoutWindSpeed.getWindSpeed("bft"), Double.MIN_VALUE);
    }







}