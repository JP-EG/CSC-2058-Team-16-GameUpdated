package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiscoverySquare {

	private List<Discovery> discoveries;

	public DiscoverySquare() {

		discoveries = new ArrayList<>();

		discoveries.add(
				new Discovery(
						1,
						"Generous Donation",
						"A generous donor has come forward to support your cause."
								+ "\nYou receive a donation of $2,000 to fund your project.",
						"Budget",
						2000));
		discoveries.add(
				new Discovery(
						2,
						"Abundant Materials Source",
						"You've uncovered a hidden stash of construction materials."
								+ "\nGain 3 additional materials units for your project.",
						"Material Units",
						3));
		discoveries.add(
				new Discovery(3,
						"Community Enthusiasm",
						"The local community is excited about your project."
								+ "\nGain 3 extra community trust points to show their support.",
						"Community Trust Points",
						3));
		discoveries.add(
				new Discovery(4,
						"Technical Breakthrough",
						"A technical breakthrough helps you in equipment procurement."
								+ "\nYou save $1,500 from your budget.",
						"Budget",
						1500));

	}

	public Discovery randomDiscovery() {
		Random random = new Random();
		int randomIndex = random.nextInt(discoveries.size());

		return discoveries.get(randomIndex);
	}

}
