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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openhab.binding.sleepiq.api.impl.GsonGenerator;
import org.openhab.binding.sleepiq.api.test.AbstractTest;

import com.google.gson.Gson;

public class BedSideStatusTest extends AbstractTest {
    private static Gson gson;

    @BeforeAll
    public static void setUpBeforeClass() {
        gson = GsonGenerator.create(true);
    }

    @Test
    public void testSerializeAllFields() throws Exception {
        BedSideStatus bedSideStatus = new BedSideStatus().withAlertDetailedMessage("No Alert").withAlertId(0L)
                .withInBed(false).withLastLink(new TimeSince().withDuration(3, 5, 4, 38)).withPressure(573)
                .withSleepNumber(55);
        assertEquals(readJson("bed-side-status.json"), gson.toJson(bedSideStatus));
    }

    @Test
    public void testDeserializeAllFields() throws Exception {
        try (FileReader reader = new FileReader(getTestDataFile("bed-side-status.json"))) {
            BedSideStatus bedSideStatus = gson.fromJson(reader, BedSideStatus.class);
            assertEquals("No Alert", bedSideStatus.getAlertDetailedMessage());
            assertEquals(Long.valueOf(0L), bedSideStatus.getAlertId());
            assertFalse(bedSideStatus.isInBed());
            assertEquals(new TimeSince().withDuration(3, 5, 4, 38), bedSideStatus.getLastLink());
            assertEquals(Integer.valueOf(573), bedSideStatus.getPressure());
            assertEquals(Integer.valueOf(55), bedSideStatus.getSleepNumber());
        }
    }
}
