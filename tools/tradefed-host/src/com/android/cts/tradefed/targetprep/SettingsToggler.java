/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.cts.tradefed.targetprep;

import com.android.tradefed.device.DeviceNotAvailableException;
import com.android.tradefed.device.ITestDevice;

/**
 * {@link SettingsToggler} sets settings by using the "adb shell content" command.
 */
public class SettingsToggler {

    /** Sets a secure setting by deleting and then inserting the string value. */
    public static void setSecureString(ITestDevice device, String name, String value)
            throws DeviceNotAvailableException {
        deleteSecure(device, name);
        device.executeShellCommand(
                "content insert"
                + " --uri content://settings/secure"
                + " --bind name:s:" + name
                + " --bind value:s:" + value);
    }

    /** Sets a secure setting by deleting and then inserting the int value. */
    public static void setSecureInt(ITestDevice device, String name, int value)
            throws DeviceNotAvailableException {
        deleteSecure(device, name);
        device.executeShellCommand(
                "content insert"
                + " --uri content://settings/secure"
                + " --bind name:s:" + name
                + " --bind value:i:" + value);
    }

    public static void updateSecureString(ITestDevice device, String name, String value)
            throws DeviceNotAvailableException {
        device.executeShellCommand(
                "content update"
                + " --uri content://settings/secure"
                + " --bind value:s:" + value
                + " --where \"name='" + name + "'\"");
    }

    public static void updateSecureInt(ITestDevice device, String name, int value)
            throws DeviceNotAvailableException {
        device.executeShellCommand(
                "content update"
                + " --uri content://settings/secure"
                + " --bind value:i:" + value
                + " --where \"name='" + name + "'\"");
    }

    private static void deleteSecure(ITestDevice device, String name)
            throws DeviceNotAvailableException {
        device.executeShellCommand(
                "content delete"
                + " --uri content://settings/secure"
                + " --where \"name='" + name + "'\"");
    }
}
