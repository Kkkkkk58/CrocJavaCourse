package ru.croc.task15;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import org.jetbrains.annotations.NotNull;
import ru.croc.task15.core.AgeGroup;
import ru.croc.task15.core.FocusGroups;
import ru.croc.task15.core.PersonalInfo;

public class Task15 {

	public static void main(String[] args) {
		List<AgeGroup> ageGroups = getAgeGroups(args);
		var focusGroups = new FocusGroups();

		Scanner input = new Scanner(System.in);
		while (true) {
			String line = input.nextLine();
			if ("END".equals(line)) {
				break;
			}
			PersonalInfo personalInfo = getPersonalInfo(line);
			AgeGroup matchingGroup = getAgeGroup(ageGroups, personalInfo);

			focusGroups.addGroupRepresentative(matchingGroup, personalInfo);
		}

		printFocusGroups(focusGroups);
	}

	private static List<AgeGroup> getAgeGroups(String[] ages) {
		List<AgeGroup> ageGroups = new ArrayList<>();

		int ageFrom = 0;
		for (String ageStr : ages) {
			int ageTo = Integer.parseInt(ageStr);
			ageGroups.add(new AgeGroup(ageFrom, ageTo));
			ageFrom = ageTo + 1;
		}
		ageGroups.add(new AgeGroup(ageFrom, null));

		return ageGroups;
	}

	@NotNull
	private static PersonalInfo getPersonalInfo(String line) {
		String[] personalInfoParts = line.split(",");
		return new PersonalInfo(personalInfoParts[0], Integer.parseInt(personalInfoParts[1]));
	}

	private static AgeGroup getAgeGroup(List<AgeGroup> ageGroups, PersonalInfo personalInfo) {
		return ageGroups
			.stream()
			.filter(ageGroup -> ageGroup.getAgeFrom() <= personalInfo.getAge()
				&& ageGroup.getAgeTo() >= personalInfo.getAge())
			.reduce((a, b) -> {
				throw new IllegalStateException("Too many elements match the predicate");
			})
			.orElseThrow(() -> new IllegalStateException("No element matches the predicate"));
	}

	private static void printFocusGroups(FocusGroups focusGroups) {
		for (Map.Entry<AgeGroup, SortedSet<PersonalInfo>> entry : focusGroups.getGroups()
			.entrySet()) {
			System.out.print(entry.getKey() + ": ");
			for (PersonalInfo personalInfo : entry.getValue()) {
				System.out.print(personalInfo + " ");
			}
			System.out.println();
		}
	}
}
