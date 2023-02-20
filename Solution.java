package assignment;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the number of Jobs");
		int n = scanner.nextInt();

		List<Job> jobs = new ArrayList<>();
		System.out.println("Enter job start time, end time, and earnings");
		for (int i = 0; i < n; i++) {
			int startTime = scanner.nextInt();
			int endTime = scanner.nextInt();
			int earnings = scanner.nextInt();
			jobs.add(new Job(startTime, endTime, earnings));
		}
		scanner.close();
		int[] result = getMaximumEarnings(jobs);
		System.out.println("The number of tasks and earnings available for others");
		System.out.println("Task: " + result[0]);
		System.out.println("Earnings: " + result[1]);
	}

	public static int[] getMaximumEarnings(List<Job> jobs) {
		Collections.sort(jobs, Comparator.comparingInt(j -> j.endTime));

		int maxEarnings = 0;
		int count = 0;
		int lastEndTime = 0;

		for (Job job : jobs) {
			if (job.startTime >= lastEndTime) {
				maxEarnings += job.earnings;
				count++;
				lastEndTime = job.endTime;
			}
		}

		int remainingCount = jobs.size() - count;
		int remainingEarnings = jobs.stream().mapToInt(j -> j.earnings).sum() - maxEarnings;
		return new int[]{remainingCount, remainingEarnings};
	}

	static class Job {
		int startTime;
		int endTime;
		int earnings;

		public Job(int startTime, int endTime, int earnings) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.earnings = earnings;
		}
	}
}


