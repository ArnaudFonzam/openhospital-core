/*
 * Open Hospital (www.open-hospital.org)
 * Copyright © 2006-2024 Informatici Senza Frontiere (info@informaticisenzafrontiere.org)
 *
 * Open Hospital is a free and open source software for healthcare data management.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * https://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.isf.utils.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

class TestFileTools {

	// The ultimate goal is to return a single Date object instead of a List of Dates.
	// Until that change is made and the corresponding changes in the GUI project
	// this helper class means we don't have to modify the tests for the change
	private Date getTimestampFromName(String fileName) {
		List<Date> dates = FileTools.getTimestampFromName(fileName);
		if (!dates.isEmpty()) {
			return dates.get(0);
		}
		return null;
	}

	@Test
	void testGetTimestampFromNameyyyydashMMdashdd_HHmmss() throws Exception {

		// Variations of "yyyy-MM-dd_HHmmss"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2021, 2, 31, 12, 0, 59);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("2021-03-31_120059");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2021-03-31_120059");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2021-03-31_120059_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("2021-03-31_120059_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimestampFromNameyyyydashMMdashddblanlHHmmss() throws Exception {

		// Variations of "yyyy-MM-dd HHmmss"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2021, 2, 31, 12, 0, 59);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("2021-03-31 120059");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2021-03-31 120059");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2021-03-31 120059_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("2021-03-31 120059_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimestampFromNameyyyydashMMdashdd_HHmm() throws Exception {

		// Variations of "yyyy-MM-dd_HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2021, 8, 11, 12, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("2021-09-11_1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2021-09-11_1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2021-09-11_1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("2021-09-11_1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimestampFromNameyyyydashMMdashddblankHHmm() throws Exception {

		// Variations of "yyyy-MM-dd HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2021, 9, 15, 12, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("2021-10-15 1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2021-10-15 1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2021-10-15 1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("2021-10-15 1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimestampFromNameyyyydashMMdashdd() throws Exception {

		// Variations of "yyyy-MM-dd"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2023, 9, 15, 12, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("2023-10-15 1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2023-10-15 1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_2023-10-15 1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("2023-10-15 1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimestampFromNameyyyyMMdd_HHmmss() throws Exception {

		// Variations of "yyyyMMdd_HHmmss"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2021, 2, 31, 12, 0, 59);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("20210331_120059");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20210331_120059");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20210331_120059_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("20210331_120059_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimestampFromNameyyyyMMddblanlHHmmss() throws Exception {

		// Variations of "yyyyMMdd HHmmss"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2021, 2, 31, 12, 0, 59);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("20210331 120059");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20210331 120059");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20210331 120059_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("20210331 120059_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimestampFromNameyyyyMMdd_HHmm() throws Exception {

		// Variations of "yyyyMMdd_HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2021, 8, 11, 12, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("20210911_1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20210911_1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20210911_1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("20210911_1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimestampFromNameyyyyMMddblankHHmm() throws Exception {

		// Variations of "yyyyMMdd HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2021, 9, 15, 12, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("20211015 1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20211015 1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20211015 1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("20211015 1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimestampFromNameyyyyMMdd() throws Exception {

		// Variations of "yyyyMMdd"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2023, 9, 15, 12, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("20231015 1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20231015 1200");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_20231015 1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("20231015 1200_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNamedddashMMdashyyyy_HHmm() throws Exception {

		// Variations of "dd-MM-yyyy_HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 11, 22, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09-03-2020_1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-2020_1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-2020_1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09-03-2020_1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNamedddashMMdashyyyyblankHHmm() throws Exception {

		// Variations of "dd-MM-yyyy HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 11, 22, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09-03-2020 1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-2020 1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-2020 1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09-03-2020_1122 text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNamedddashMMdashyyyy() throws Exception {

		// Variations of "dd-MM-yyyy"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09-03-2020");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-2020");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-2020_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09-03-2020text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNamedddashMMdashyy_HHmm() throws Exception {

		// Variations of "dd-MM-yy_HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2037, 2, 9, 11, 22, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09-03-37_1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-37_1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-37_1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09-03-37_1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNamedddashMMdashyyblankHHmm() throws Exception {

		// Variations of "dd-MM-yy HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 11, 22, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09-03-20 1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-20 1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09-03-20 1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09-03-20_1122 text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNamedddashMMdashyy() throws Exception {

		// Variations of "dd-MM-yy"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 22, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("22-03-20");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_22-03-20");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_22-03-20_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("22-03-20text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNameddslashMMslashyyyy_HHmm() throws Exception {

		// Variations of "dd/MM/yyyy_HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 11, 22, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09/03/2020_1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/2020_1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/2020_1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09/03/2020_1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNameddslashMMslashyyyyblankHHmm() throws Exception {

		// Variations of "dd/MM/yyyy HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 11, 22, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09/03/2020 1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/2020 1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/2020 1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09/03/2020_1122 text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNameddslashMMslashyyyy() throws Exception {

		// Variations of "dd/MM/yyyy"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09/03/2020");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/2020");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/2020_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09/03/2020text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNameddslashMMslashyy_HHmm() throws Exception {

		// Variations of "dd/MM/yy_HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 11, 22, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09/03/20_1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/20_1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/20_1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09/03/20_1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNameddslashMMslashyyblankHHmm() throws Exception {

		// Variations of "dd/MM/yy HHmm"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 11, 22, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09/03/20 1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/20 1122");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/20 1122_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09/03/20_1122 text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampFromNameddslashMMslashyy() throws Exception {

		// Variations of "dd/MM/yy"
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.set(2020, 2, 9, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date timestampFromName = getTimestampFromName("09/03/20");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/20");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("some-Text_09/03/20_text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());

		timestampFromName = getTimestampFromName("09/03/20text.txt");
		assertThat(timestampFromName).isEqualTo(calendar.getTime());
	}

	@Test
	void testGetTimeStampNoThere() throws Exception {
		assertThat(getTimestampFromName(null)).isNull();
		assertThat(getTimestampFromName("")).isNull();
		assertThat(getTimestampFromName("justText")).isNull();
		assertThat(getTimestampFromName("not0101amatch")).isNull();
	}

	@Test
	void testHumanReadableByteCountParse() throws Exception {
		assertThat(FileTools.humanReadableByteCountParse("1024B")).isEqualTo(1024L);
		assertThat(FileTools.humanReadableByteCountParse("4M")).isEqualTo(4194304L);
		assertThat(FileTools.humanReadableByteCountParse("16M")).isEqualTo(16777216L);
		assertThat(FileTools.humanReadableByteCountParse("256M")).isEqualTo(268435456L);
		assertThat(FileTools.humanReadableByteCountParse("1G")).isEqualTo(1073741824L);
	}

	@Test
	void testHumanReadableByteCount() throws Exception {
		assertThat(FileTools.humanReadableByteCount(1024L, Locale.US)).isEqualTo("1.0 B");
		assertThat(FileTools.humanReadableByteCount(4194304L, Locale.US)).isEqualTo("4.0 M");
		assertThat(FileTools.humanReadableByteCount(16777216L, Locale.US)).isEqualTo("16.0 M");
		assertThat(FileTools.humanReadableByteCount(268435456L, Locale.US)).isEqualTo("256.0 M");
		assertThat(FileTools.humanReadableByteCount(1073741824L, Locale.US)).isEqualTo("1.0 G");
	}

	@Test
	void testReadFileToStringLineByLineWithHtml() {
		File file = getFile("testFile.txt");
		String result = FileTools.readFileToStringLineByLine(file.getAbsolutePath(), true);

		assertThat(result).isNotNull();
		assertThat(result).startsWith("<html>");
		assertThat(result).endsWith("</html>");
		assertThat(result).contains("<br>");
		assertThat(result).contains("This could be one line text");
		assertThat(result).contains("or text with");
	}

	@Test
	void testReadFileToStringLineByLineWithoutHtml() {
		File file = getFile("testFile.txt");
		String result = FileTools.readFileToStringLineByLine(file.getAbsolutePath(), false);

		assertThat(result).isNotNull();
		assertThat(result).doesNotStartWith("<html>");
		assertThat(result).doesNotEndWith("</html>");
		assertThat(result).doesNotContain("<br>");
		assertThat(result).contains("This could be one line text");
		assertThat(result).contains("or text with");
	}

	@Test
	void testReadNonExistentFile() {
		assertThrows(IllegalArgumentException.class, () -> FileTools.readFileToStringLineByLine("nonexistent.txt", true));
	}

	@Test
	void testGetTimestampNullFile() {
		assertThat(FileTools.getTimestamp(null)).isNull();
	}

	@Test
	void testGetTimestamp() {
		File file = getFile("testFile.txt");
		LocalDateTime timeStamp = FileTools.getTimestamp(file);
		assertThat(timeStamp).isNotNull();
		assertThat(timeStamp.getYear()).isGreaterThanOrEqualTo(2024);
	}

	@Test
	void testGetTimestampFromName() {
		File file = getFile("testFile.txt");
		List<Date> dates = FileTools.getTimestampFromName(file);
		assertThat(dates).isEmpty();
	}

	@Test
	void testGetFile() {
		assertThat(FileTools.getFile("badTestFile.txt")).isNull();
	}

	private File getFile(String fileName) {
		return new File(getClass().getResource(fileName).getFile());
	}
}
