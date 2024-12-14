import java.util.*;

public class SocialNetworkGraph {
    // Adjacency list to represent the graph
    private Map<String, List<String>> adjacencyList;

    public SocialNetworkGraph() {
        adjacencyList = new HashMap<>();
    }

    // Add a user to the network
    public void addUser(String user) {
        adjacencyList.putIfAbsent(user, new ArrayList<>());
    }

    // Add a friendship (undirected edge)
    public void addFriendship(String user1, String user2) {
        addUser(user1);
        addUser(user2);
        adjacencyList.get(user1).add(user2);
        adjacencyList.get(user2).add(user1);
    }

    // Print all users and their friends
    public void printNetwork() {
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + "'s friends: ");
            for (String friend : entry.getValue()) {
                System.out.print(friend + " ");
            }
            System.out.println();
        }
    }

    // Get all friends of a specific user
    public List<String> getFriends(String user) {
        return adjacencyList.getOrDefault(user, new ArrayList<>());
    }

    // Suggest friends to a user based on mutual friends
    public List<String> suggestFriends(String user) {
        Set<String> suggestedFriends = new HashSet<>();
        List<String> friends = getFriends(user);

        for (String friend : friends) {
            List<String> friendsOfFriend = getFriends(friend);
            for (String potentialFriend : friendsOfFriend) {
                if (!potentialFriend.equals(user) && !friends.contains(potentialFriend)) {
                    suggestedFriends.add(potentialFriend);
                }
            }
        }

        return new ArrayList<>(suggestedFriends);
    }

    public static void main(String[] args) {
        SocialNetworkGraph network = new SocialNetworkGraph();

        // Add users and friendships
        network.addFriendship("Alice", "Bob");
        network.addFriendship("Alice", "Charlie");
        network.addFriendship("Bob", "David");
        network.addFriendship("Charlie", "David");

        // Print the network
        System.out.println("Social Network:");
        network.printNetwork();

        // Get and display friends of a specific user
        System.out.println("\nFriends of Alice:");
        System.out.println(network.getFriends("Alice"));

        // Suggest friends for a user
        System.out.println("\nFriend suggestions for Alice:");
        System.out.println(network.suggestFriends("Alice"));
    }
}
