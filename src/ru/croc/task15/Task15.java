package ru.croc.task15;

import ru.croc.task15.core.AgeGroup;
import ru.croc.task15.core.PersonalInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Task15 {

	public static void main(String[] args) {
		List<AgeGroup> ageGroups = getAgeGroups(args);
		Map<AgeGroup, SortedSet<PersonalInfo>> map = new TreeMap<>(Comparator.comparingInt(AgeGroup::getAgeFrom).reversed());

		Scanner input = new Scanner(System.in);
		while (true) {
			String line = input.nextLine();
			if ("END".equals(line)) {
				break;
			}
			String[] personalInfoParts = line.split(",");
			PersonalInfo personalInfo = new PersonalInfo(personalInfoParts[0], Integer.parseInt(personalInfoParts[1]));
			AgeGroup matchingGroup = ageGroups
				.stream()
				.filter(ageGroup -> ageGroup.getAgeFrom() <= personalInfo.getAge()
				&& (ageGroup.getAgeTo() == null || ageGroup.getAgeTo() >= personalInfo.getAge()))
				.reduce((a, b) -> {
					throw new IllegalStateException("Too many elements match the predicate");
				})
				.orElseThrow(() -> new IllegalStateException("No element matches the predicate"));

			if (map.containsKey(matchingGroup)) {
				map.get(matchingGroup).add(personalInfo);
			}
			else {
				SortedSet<PersonalInfo> set = new TreeSet<>(
					Comparator.comparingInt(PersonalInfo::getAge).reversed().thenComparing(PersonalInfo::getName));
				set.add(personalInfo);
				map.put(matchingGroup, set);
			}
		}

		for (Map.Entry<AgeGroup, SortedSet<PersonalInfo>> entry : map.entrySet()) {
			System.out.print(entry.getKey() + ": ");
			for (PersonalInfo personalInfo : entry.getValue()) {
				System.out.print(personalInfo + " ");
			}
			System.out.println();
		}
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
}
