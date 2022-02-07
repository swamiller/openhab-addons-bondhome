/**
 * Copyright 2017-2018 Gregory Moyer and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openhab.binding.lametrictime.api.local.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openhab.binding.lametrictime.api.common.impl.GsonGenerator;
import org.openhab.binding.lametrictime.api.test.AbstractTest;

import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;

public class WidgetTest extends AbstractTest
{
    private static Gson gson;

    @BeforeAll
    public static void setUpBeforeClass()
    {
        gson = GsonGenerator.create(true);
    }

    @Test
    @SuppressWarnings("serial")
    public void testSerialize() throws Exception
    {
        Widget widget = new Widget().withPackageName("com.lametric.radio")
                                    .withIndex(Integer.valueOf(-1))
                                    .withSettings(new HashMap<String, JsonPrimitive>()
                                    {
                                        {
                                            put("_title", new JsonPrimitive("Radio"));
                                        }
                                    });
        assertEquals(readJson("widget.json"), gson.toJson(widget));
    }

    @Test
    @SuppressWarnings("serial")
    public void testDeserialize() throws Exception
    {
        try (FileReader reader = new FileReader(getTestDataFile("widget.json")))
        {
            Widget widget = gson.fromJson(reader, Widget.class);
            assertEquals("com.lametric.radio", widget.getPackageName());
            assertEquals(Integer.valueOf(-1), widget.getIndex());
            assertEquals(new HashMap<String, JsonPrimitive>()
            {
                {
                    put("_title", new JsonPrimitive("Radio"));
                }
            }, widget.getSettings());
        }
    }
}
