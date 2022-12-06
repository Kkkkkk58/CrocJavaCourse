package ru.croc.task15.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class FocusGroups {
	private final Map<AgeGroup, SortedSet<PersonalInfo>> groups;

	public FocusGroups() {
		groups = new TreeMap<>(Comparator.comparingInt(AgeGroup::getAgeFrom).reversed());
	}

	public void addGroupRepresentative(AgeGroup ageGroup, PersonalInfo personalInfo) {
		if (groups.containsKey(ageGroup)) {
			groups.get(ageGroup).add(personalInfo);
		}
		else {
			SortedSet<PersonalInfo> set = new TreeSet<>(Comparator.comparingInt(PersonalInfo::getAge).reversed().thenComparing(PersonalInfo::getName));
			set.add(personalInfo);
			groups.put(ageGroup, set);
		}
	}

	public Map<AgeGroup, SortedSet<PersonalInfo>> getGroups() {
		return Collections.unmodifiableMap(groups);
	}
}
