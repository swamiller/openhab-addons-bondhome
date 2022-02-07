/*
 * Copyright 2017 Gregory Moyer
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
package org.openhab.binding.sleepiq.api.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openhab.binding.sleepiq.api.impl.GsonGenerator;
import org.openhab.binding.sleepiq.api.test.AbstractTest;

import com.google.gson.Gson;

public class SleepersResponseTest extends AbstractTest {
    private static Gson gson;

    @BeforeAll
    public static void setUpBeforeClass() {
        gson = GsonGenerator.create(true);
    }

    @Test
    public void testSerializeAllFields() throws Exception {
        SleepersResponse sleepersResponse = new SleepersResponse()
                .withSleepers(Arrays.asList(new Sleeper().withFirstName("Alice"), new Sleeper().withFirstName("Bob")));
        assertEquals(readJson("sleepers-response.json"), gson.toJson(sleepersResponse));
    }

    @Test
    public void testDeserializeAllFields() throws Exception {
        try (FileReader reader = new FileReader(getTestDataFile("sleepers-response.json"))) {
            SleepersResponse sleepersResponse = gson.fromJson(reader, SleepersResponse.class);

            List<Sleeper> sleepers = sleepersResponse.getSleepers();
            assertNotNull(sleepers);
            assertEquals(2, sleepers.size());
            assertEquals("Alice", sleepers.get(0).getFirstName());
            assertEquals("Bob", sleepers.get(1).getFirstName());
        }
    }
}
