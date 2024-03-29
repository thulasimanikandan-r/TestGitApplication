<?xml version="1.0" encoding="utf-8"?>
<!--
/* //device/apps/common/res/any/layout/resolve_list_item.xml
**
** Copyright 2006, The Android Open Source Project
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
**     http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/
-->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:orientation="horizontal"
              android:layout_height="wrap_content"
              android:layout_width="match_parent"
              android:minHeight="?attr/listPreferredItemHeightSmall"
              android:paddingTop="4dp"
              android:paddingBottom="4dp"
              android:background="?attr/activatedBackgroundIndicator">

    <!-- Activity icon when presenting dialog
         Size will be filled in by ResolverActivity -->
    <ImageView android:id="@+id/icon"
               android:layout_width="24dp"
               android:layout_height="24dp"
               android:layout_gravity="start|center_vertical"
               android:layout_marginStart="?attr/listPreferredItemPaddingStart"
               android:layout_marginTop="12dp"
               android:layout_marginBottom="12dp"
               android:scaleType="fitCenter" />

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:gravity="start|center_vertical"
              android:orientation="vertical"
              android:paddingStart="?attr/listPreferredItemPaddingStart"
              android:paddingEnd="?attr/listPreferredItemPaddingEnd"
              android:layout_height="wrap_content"
              android:layout_width="wrap_content"
              android:layout_gravity="start|center_vertical">
        <!-- Activity name -->
        <TextView android:id="@android:id/text1"
                 