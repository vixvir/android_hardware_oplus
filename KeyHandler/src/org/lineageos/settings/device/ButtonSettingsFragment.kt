/*
 * Copyright (C) 2021 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.device

import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import androidx.preference.Preference
import androidx.preference.PreferenceFragment
import androidx.preference.SwitchPreferenceCompat

class ButtonSettingsFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener {
    private lateinit var sliderDozeSwitch: SwitchPreferenceCompat

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.button_panel)
        activity.actionBar!!.setDisplayHomeAsUpEnabled(true)

        sliderDozeSwitch = findPreference<SwitchPreferenceCompat>(KeyHandler.SLIDER_DOZE_ENABLED)!!
        sliderDozeSwitch.isChecked = KeyHandler.isSliderDozeEnabled(context)
        sliderDozeSwitch.onPreferenceChangeListener = this
    }

    override fun addPreferencesFromResource(preferencesResId: Int) {
        super.addPreferencesFromResource(preferencesResId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                activity.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
        if (preference == sliderDozeSwitch) {
            val enabled = newValue as Boolean
            Settings.System.putInt(context.contentResolver,
                    KeyHandler.SLIDER_DOZE_ENABLED_SETTING, if (enabled) 1 else 0)
            return true
        }
        return false
    }

    companion object {

    } 
}
