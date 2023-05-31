package com.mastercard.gpse.processing.utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class RandomUtils {

	private static final Logger logger = (Logger) LogManager.getLogger(RandomUtils.class);

	/**
	 * Generates a random alphabetic string for specified Length
	 *
	 * @param length - length of the output string
	 * @return alphabetic string
	 */
	public static String getRandomAlphabeticString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	/**
	 * Generates a random alphanumeric string for specified length
	 *
	 * @param length - length of the output string
	 * @return alphanumeric string
	 */
	public static String getRandomAlphanumericString(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	/**
	 * Generates a random String with special characters
	 *
	 * @return random password
	 */
	private static String getRandomSpecialCharString() {
		return RandomStringUtils.random(5, "!@#$*()*&^%");
	}

	/**
	 * Generates a random numeric string for specified length
	 *
	 * @param length - length of the output string
	 * @return numeric string
	 */
	public static String getRandomNumericString(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public static String getFourDigitRandomNumber(){
		Random r = new Random();
		String randomNumber = String.format("%04d", Integer.valueOf(r.nextInt(1001)));
		return randomNumber;
	}

	public static String getNineDigitRandomNumber(){
		Random r = new Random();
		String randomNumber = String.format("%05d", r.nextInt(199999999));
		return randomNumber;
	}

	/**
	 * Generates random integer between the given min and max bounds in string type
	 * 
	 * @param min - minimum bound
	 * @param max - maximum bound
	 * @return integer number in string type
	 */
	public static String getRandomNumberBetween(int min, int max) {
		return String.valueOf(new Random().nextInt(max - min) + min);
	}

	/**
	 * Generates random integer between the given min and max bounds
	 * 
	 * @param min - minimum bound (inclusive)
	 * @param max - maximum bound (exclusive)
	 * @return integer
	 */
	public static int getRandomIntegerBetween(int min, int max) {
		return new Random().nextInt(max - min) + min;
	}

	/**
	 * Generates a random String with special characters
	 * 
	 * @return random password
	 */
	private static String getRandomStringWithSpecialChar() {

		String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
		String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
		String numbers = RandomStringUtils.randomNumeric(2);
		String specialChar = RandomStringUtils.random(5, "!@#$*()*&^%");
		String totalChars = RandomStringUtils.randomAlphanumeric(3);
		String combinedChars = upperCaseLetters.concat(lowerCaseLetters).concat(numbers).concat(specialChar)
				.concat(totalChars);
		List<Character> pwdChars = combinedChars.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Collections.shuffle(pwdChars);
		String randomString = pwdChars.stream()
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
		return randomString;
	}

	/**
	 * Generates a UUID. The UUID is generated using a cryptographically strong pseudo random number generator.
	 *
	 * @return UUID
	 */
	public static String getUUId() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Generate a UUID with specified length. The UUID is generated using a cryptographically strong pseudo random number generator.
	 */
	public static String getUUId(int length) {
		String randomUuid = UUID.randomUUID().toString();
		randomUuid = randomUuid.replace("-", "").substring(0, length);
		loggInfo("UUID: " + randomUuid);
		return randomUuid;
	}

	/**
	 * Return the random first name from the list
	 *
	 * @return first name
	 */
	public static String getRandomFirstName() {
		String[] firstNames = {"ABC", "JOHN", "JACOB", "JON", "JACK", "SMITH","ROB", "TOM","JERRY","KAT","LISA","AMY","ROBERT","RAF","RON","BRAD"};
		return  firstNames[new Random().nextInt(firstNames.length - 1)];
	}

	/**
	 * Return the random last name from the list
	 *
	 * @return last name
	 */
	public static String getRandomLastName() {
		String[] lastNames = {"SMITH", "JOHNSON", "DHARM", "KUL","PAT","BRAD","STILL","MARK","TEST","POST","MAN"};
		return  lastNames[new Random().nextInt(lastNames.length - 1)];
	}

	/**
	 * Generate random email id with 'Auto_Email_' prefix in yopmail domain
	 *
	 * @param length - length of the email address
	 * @return email address
	 */
	public static String getRandomEmail(int length) {
		return RandomStringUtils.randomAlphanumeric(length-12) + "@yopmail.com";
	}

	/**
	 * This method is used to generate dynamic name based on current date time
	 *
	 * @return address
	 */
	public static String getRandomAddress() {
		return "Street - " + RandomStringUtils.randomAlphabetic(6);
	}

	/**
	 * Generate the random last name with 'Auto_LN_' prefix
	 *
	 * @return last name
	 */
	public static String getRandomDOB() {
		return DateUtility.minusDaysFromCurrentDate(getRandomIntegerBetween(6570, 36500), "yyyy-MM-dd");
	}

	/**
	 * Generate random phone number as per Polish format<br><br>
	 * Cell phone numbers in Poland are 9 digit e.g. 605-555-555 and mostly start with 5,6,7<br>
	 * +48 (Polish international code)
	 *
	 * @return phone number
	 */
	public static String getRandomPhoneNumber() {
		String m[] = new String[10];
		for (int i = 1; i < 9; i++) {
			m[i] = Integer.toString(new Random().nextInt(10));
		}
		m[0] = String.valueOf(getRandomIntegerBetween(5,8));
		String randomPhonenum = m[0] + m[1] + m[2] + m[3] + m[4] + m[5] + m[6] + m[7] + m[8];
		return randomPhonenum;
	}

	/**
	 * Logs the informational message
	 * 
	 * @param message - message to be logged
	 */
	public static void loggInfo(String message) {
		logger.info(message);
	}
}
