/*
 * Copyright (C) 2016-2025 Álinson Santos Xavier <git@axavier.org>
 *
 * This file is part of Loop Habit Tracker.
 *
 * Loop Habit Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Loop Habit Tracker is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.isoron.uhabits.core.database.migrations

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.isoron.uhabits.core.BaseUnitTest
import org.isoron.uhabits.core.database.Database
import org.isoron.uhabits.core.database.MigrationHelper
import org.junit.Test

class Version26Test : BaseUnitTest() {
    private lateinit var db: Database
    private lateinit var helper: MigrationHelper

    override fun setUp() {
        super.setUp()
        db = openDatabaseResource("/databases/022.db")
        helper = MigrationHelper(db)
    }

    @Test
    fun `test migrate to 26 adds frequency mode column with days default`() {
        helper.migrateTo(26)
        db.query("select freq_mode from habits") { c ->
            assertThat(c.getInt(0), equalTo(0))
        }
    }
}
