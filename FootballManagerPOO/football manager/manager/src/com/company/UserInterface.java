package com.company;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class UserInterface {

    private final int numberOfClubs;
    private final ArrayList<Team> teams;
    private final Scanner scanner;
    private final ArrayList<Match> matches;
    private final List<Player> unassignedPlayers;

    public UserInterface(int numberOfClubs) {
        this.numberOfClubs = numberOfClubs;
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.unassignedPlayers = new ArrayList<>();
        populate();
        displayMenu();

    }

    private void populate() {
        try (Scanner scanner = new Scanner(Paths.get("manager/src/com/company/logsV2.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] linhaPartida = line.split(":");
                switch (linhaPartida[0]) {
                    case "Equipa":
                        Team team = new Team(linhaPartida[1]);
                        do {
                            line = scanner.nextLine();
                            linhaPartida = line.split(":");
                            switch (linhaPartida[0]) {
                                case "Guarda-Redes":
                                    String[] campos = linhaPartida[1].split(",");
                                    String nomeJog = campos[0];
                                    int spd = Integer.parseInt(campos[1]);
                                    int end = Integer.parseInt(campos[2]);
                                    int dex = Integer.parseInt(campos[3]);
                                    int imp = Integer.parseInt(campos[4]);
                                    int hg = Integer.parseInt(campos[5]);
                                    int kick = Integer.parseInt(campos[6]);
                                    int pass = Integer.parseInt(campos[7]);
                                    int elast = Integer.parseInt(campos[8]);
                                    Goalkeeper gk = new Goalkeeper(nomeJog, spd, end, dex, imp, hg, kick, pass, elast, new ArrayList<>());
                                    gk.addTeamToHistory(team);
                                    team.addPlayer(gk);
                                    break;
                                case "Lateral":
                                    campos = linhaPartida[1].split(",");
                                    nomeJog = campos[0];
                                    spd = Integer.parseInt(campos[1]);
                                    end = Integer.parseInt(campos[2]);
                                    dex = Integer.parseInt(campos[3]);
                                    imp = Integer.parseInt(campos[4]);
                                    hg = Integer.parseInt(campos[5]);
                                    kick = Integer.parseInt(campos[6]);
                                    pass = Integer.parseInt(campos[7]);
                                    int cross = Integer.parseInt(campos[8]);
                                    Lateral lat = new Lateral(nomeJog, spd, end, dex, imp, hg, kick, pass, cross, new ArrayList<>());
                                    lat.addTeamToHistory(team);
                                    team.addPlayer(lat);
                                    break;
                                case "Defesa":
                                    campos = linhaPartida[1].split(",");
                                    nomeJog = campos[0];
                                    spd = Integer.parseInt(campos[1]);
                                    end = Integer.parseInt(campos[2]);
                                    dex = Integer.parseInt(campos[3]);
                                    imp = Integer.parseInt(campos[4]);
                                    hg = Integer.parseInt(campos[5]);
                                    kick = Integer.parseInt(campos[6]);
                                    pass = Integer.parseInt(campos[7]);
                                    Defender def = new Defender(nomeJog, spd, end, dex, imp, hg, kick, pass, new ArrayList<>());
                                    def.addTeamToHistory(team);
                                    team.addPlayer(def);
                                    break;
                                case "Medio":
                                    campos = linhaPartida[1].split(",");
                                    nomeJog = campos[0];
                                    spd = Integer.parseInt(campos[1]);
                                    end = Integer.parseInt(campos[2]);
                                    dex = Integer.parseInt(campos[3]);
                                    imp = Integer.parseInt(campos[4]);
                                    hg = Integer.parseInt(campos[5]);
                                    kick = Integer.parseInt(campos[6]);
                                    pass = Integer.parseInt(campos[7]);
                                    int rec = Integer.parseInt(campos[8]);
                                    Midfielder mid = new Midfielder(nomeJog, spd, end, dex, imp, hg, kick, pass, rec, new ArrayList<>());
                                    mid.addTeamToHistory(team);
                                    team.addPlayer(mid);
                                    break;
                                case "Avancado":
                                    campos = linhaPartida[1].split(",");
                                    nomeJog = campos[0];
                                    spd = Integer.parseInt(campos[1]);
                                    end = Integer.parseInt(campos[2]);
                                    dex = Integer.parseInt(campos[3]);
                                    imp = Integer.parseInt(campos[4]);
                                    hg = Integer.parseInt(campos[5]);
                                    kick = Integer.parseInt(campos[6]);
                                    pass = Integer.parseInt(campos[7]);
                                    Forward forw = new Forward(nomeJog, spd, end, dex, imp, hg, kick, pass, new ArrayList<>());
                                    forw.addTeamToHistory(team);
                                    team.addPlayer(forw);
                                    break;
                            }
                        } while (scanner.hasNextLine() && !linhaPartida[0].equals("Equipa"));
                        teams.add(team);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void anyKey() {
        System.out.println("Enter any key");
        scanner.nextLine();
    }

    private void displayMenu() {

        while (true) {
            System.out.println("==== Football Manager ====");
            System.out.println("1: New player");
            System.out.println("2: New team");
            System.out.println("3: Quick Match");
            System.out.println("4: Delete team");
            System.out.println("5: Team statistics");
            System.out.println("6: Display the Premier League Table");
            System.out.println("7: Add a Played Match");
            System.out.println("8: Display Calendar and Find Match");
            System.out.println("9: Player Transfer");
            System.out.println("0: Exit");
            String line = scanner.nextLine();
            int command = 0;
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) { // TODO: Este catch não está a fazer nada. Tirar completamente ou corrigir.
            }

            switch (command) {
                case 1:
                    unassignedPlayers.add(createPlayer());
                    break;
                case 2:
                    addTeam();
                    break;
                case 3:
                    quickMatch();
                    break;
                case 4:
                    deleteTeam();
                    break;
                case 5:
                    displayStatistics();
                    break;
                case 6:
                    displayLeagueTable();
                    break;
                case 7:
                    addPlayedMatch();
                    break;
                case 8:
                    displayCalendar();
                    break;
                case 9:
                    playerTrans();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong Command");
            }

        }
    }

    private void quickMatch() {
        while (true) {
            int teamAValid = 0;
            System.out.println("==== Quick Match ====");
            System.out.println("Available teams:");
            printTeams();
            System.out.println("Select Team A");
            int teamAIdx = Integer.parseInt(scanner.nextLine()) - 1;
            if (teamAIdx >= teams.size() || teamAIdx < 0) {
                System.out.println("Select a team from the list");
                anyKey();
            } else teamAValid = 1;
            if (teamAValid == 1) {
                System.out.println("Select Team B");
                int teamBIdx = Integer.parseInt(scanner.nextLine()) - 1;
                if (teamBIdx >= teams.size() || teamBIdx < 0) {
                    System.out.println("Select a team from the list");
                    anyKey();
                } else if (teamAIdx == teamBIdx) {
                    System.out.println("The two team must be different");
                    anyKey();
                } else {
                    Team teamA = teams.get(teamAIdx);
                    Team teamB = teams.get(teamBIdx);
                    Match match = new QuickMatch(teamA, teamB);

                    printPlayers(teamA);
                    System.out.println("Team A: Select the starting players");
                    startingPlayersSelection(teamA, match);
                    printPlayers(teamB);
                    System.out.println("Team B: Select the starting players");
                    startingPlayersSelection(teamB, match);
                    if (teams.get(teamAIdx).totalAbility() > teams.get(teamBIdx).totalAbility()) {
                        System.out.println("team1 Wins");
                        anyKey();
                    } else if (teams.get(teamAIdx).totalAbility() < teams.get(teamBIdx).totalAbility()) {
                        System.out.println("team2 Wins");
                        anyKey();
                    } else {
                        System.out.println("It's a Draw");
                        anyKey();
                    }
                    return;
                }
            }
        }
    }

    private void startingPlayersSelection(Team teamA, Match match) {
        for (int i = 0; i < 11; i++) {
            int playerIdx = Integer.parseInt(scanner.nextLine());
            if (playerIdx < 1 || playerIdx > 20) {
                System.out.println("No such player. Choose another one");
            } else {
                Player p = teamA.getPlayers().get(playerIdx);
                if (p.getState() instanceof Starting) {
                    System.out.println("This player is already a starting player. Choose another one");
                    i--;
                } else {
                    try {
                        match.setStartingPlayer(p, teamA);
                    } catch (ChangePlayerStateExeption changePlayerStateExeption) {
                        changePlayerStateExeption.printStackTrace();
                    }
                }
            }
        }
    }

    private void printTeams() {
        int i = 1;
        for (Team t : teams) {
            System.out.println(i + ": " + t.getName());
            i++;
        }
    }

    private Player createPlayer() {
        while (true) {
            System.out.println("==== Player Creation ====");
            System.out.println("What is the role?");
            System.out.println("Goalkeeper (press 1)");
            System.out.println("Defender (press 2)");
            System.out.println("Lateral (press 3)");
            System.out.println("Midfielder (press 4)");
            System.out.println("Forward (press 5)");
            String line = scanner.nextLine();
            int command = 0;
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {
            }
            if (command < 6 && command > 0) {
                System.out.println("Name:");
                String name = scanner.nextLine();
                System.out.println("Speed:");
                int spd = Integer.parseInt(scanner.nextLine());
                System.out.println("Endurance:");
                int end = Integer.parseInt(scanner.nextLine());
                System.out.println("Dexterity:");
                int dex = Integer.parseInt(scanner.nextLine());
                System.out.println("Strength:");
                int str = Integer.parseInt(scanner.nextLine());
                System.out.println("HeadGame:");
                int hg = Integer.parseInt(scanner.nextLine());
                System.out.println("Kick:");
                int kick = Integer.parseInt(scanner.nextLine());
                System.out.println("Pass:");
                int pass = Integer.parseInt(scanner.nextLine());

                switch (command) {
                    case 1:
                        System.out.println("Elasticity:");
                        int elast = Integer.parseInt(scanner.nextLine());
                        return new Goalkeeper(name, spd, end, dex, str, hg, kick, pass, elast, new ArrayList<>());
                    case 2:
                        return new Defender(name, spd, end, dex, str, hg, kick, pass, new ArrayList<>());
                    case 3:
                        System.out.println("Cross:");
                        int cross = Integer.parseInt(scanner.nextLine());
                        return new Lateral(name, spd, end, dex, str, hg, kick, pass, cross, new ArrayList<>());
                    case 4:
                        System.out.println("Recovery:");
                        int rec = Integer.parseInt(scanner.nextLine());
                        return new Midfielder(name, spd, end, dex, str, hg, kick, pass, rec, new ArrayList<>());
                    case 5:
                        return new Forward(name, spd, end, dex, str, hg, kick, pass, new ArrayList<>());
                    default:
                        System.out.println("Wrong Command");
                }
            } else System.out.println("Wrong Command");
        }
    }

    private void addTeam() {
        if (teams.size() == numberOfClubs) {
            System.out.println("Can't add more clubs to league");
            return;
        }
        System.out.println("==== Team Creation ====");
        System.out.println("Insert Club Name: ");
        String name = scanner.nextLine();

        //if(league.contains(name)){
        //    System.out.println("This club is already in the league");
        //    anyKey();
        //    return;
        //}

        Team team = new Team(name);
        while (true) {

            System.out.println("Add existing player (press 1)");
            System.out.println("Create new player (press 2)");
            System.out.println("Return (press 0)");
            String line = scanner.nextLine();
            int command = 0;
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {
            }
            switch (command) {
                case 1:
                    if (unassignedPlayers.size() == 0) {
                        System.out.println("There are no players. Please create a new one.");
                        anyKey();
                        break;
                    }
                    printUnassignedPlayers();
                    int playerIndex = Integer.parseInt(scanner.nextLine());
                    Player player = unassignedPlayers.get(playerIndex);
                    team.addPlayer(player);
                    unassignedPlayers.remove(playerIndex);
                    player.addTeamToHistory(team);
                    break;
                case 2:
                    team.addPlayer(createPlayer());
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong Command");
            }
            teams.add(team);
        }


    }

    private void printUnassignedPlayers() {
        int i = 0;
        for (Player player : this.unassignedPlayers) {
            System.out.println(i + ": " + player);
            i++;
        }
    }

    private void deleteTeam() {
        System.out.println("Insert club name: ");
        String line = scanner.nextLine();

        for (Team t : teams) {
            if (t.getName().equals(line)) {
                List<Player> unasignedPlayers = t.removeAndReturnAllPlayers();
                System.out.println("Team " + t.getName() + " removed");

                anyKey();
                return;
            }
        }

        printTeams();

        System.out.println("No such Team");
        anyKey();
    }

    private void displayStatistics() {
        System.out.println("Insert club name: ");
        String line = scanner.nextLine();
        for (Team club : teams) {
            if (club.getName().equals(line)) {
                System.out.println("Come back later. Nothing to see here.");
                anyKey();
                return;
            }
        }
        System.out.println("No such club in league");
        anyKey();
    }

    private void displayLeagueTable() {
        teams.sort(new CustomComparator());
        int i = 1;
        int j = 1;
        for (Team club : teams) {
            System.out.println(i + ": " + club);
            i++;
        }
        System.out.println("Choose any club:");
        System.out.println("Press 0 to exit:");
        int club = Integer.parseInt(scanner.nextLine());
        if(club==0) return;
        System.out.println(club);
        teams.get(club-1).getPlayers().sort(new PlayerComparator());
        for (Player p : teams.get(club-1).getPlayers()) {
            System.out.println(j + ": " + p);
            j++;
        }
    }

    private void addPlayedMatch() {

        System.out.println("Enter date (format mm-dd-yyyy): ");
        String line = scanner.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("MM-dd-yyyy").parse(line);
        } catch (ParseException ex) {
            System.out.println("You have to enter date in format mm-dd-yyyy");
            return;
        }

        System.out.println("Enter Home Team: ");
        line = scanner.nextLine();
        Team home = null;
        for (Team club : teams) {
            if (club.getName().equals(line))
                home = club;
        }
        if (home == null) {
            System.out.println("No such club in league");
            return;
        }
        System.out.println("Enter Away Team: ");
        line = scanner.nextLine();
        Team away = null;
        for (Team club : teams) {
            if (club.getName().equals(line))
                away = club;
        }
        if (away == null) {
            System.out.println("No such club in league");
            return;
        }

        System.out.println("Enter home team goals: ");
        line = scanner.nextLine();
        int homeGoals = -1;
        try {
            homeGoals = Integer.parseInt(line);
        } catch (Exception e) {
        }
        if (homeGoals == -1) {
            System.out.println("You have to enter number of goals");
            return;
        }

        System.out.println("Enter away team goals: ");
        line = scanner.nextLine();
        int awayGoals = -1;
        try {
            awayGoals = Integer.parseInt(line);
        } catch (Exception e) {
        }
        if (awayGoals == -1) {
            System.out.println("You have to enter number of goals");
            return;
        }


        Match match = new QuickMatch(home, away);
        match.setDate(date);
        match.setTeamAScore(awayGoals);
        match.setTeamBScore(homeGoals);
        matches.add(match);
        home.setScoredGoalsCount(home.getScoredGoalsCount() + homeGoals);
        away.setScoredGoalsCount(away.getScoredGoalsCount() + awayGoals);
        home.setReceivedGoalsCount(home.getReceivedGoalsCount() + awayGoals);
        away.setReceivedGoalsCount(away.getReceivedGoalsCount() + homeGoals);
        home.setMatchesPlayed(home.getMatchesPlayed() + 1);
        away.setMatchesPlayed(away.getMatchesPlayed() + 1);

        if (homeGoals > awayGoals) {
            home.setPoints(home.getPoints() + 3);
            home.setWinCount(home.getWinCount() + 1);
            away.setDefeatCount(away.getDefeatCount() + 1);
        } else if (homeGoals < awayGoals) {
            away.setPoints(away.getPoints() + 3);
            away.setWinCount(away.getWinCount() + 1);
            home.setDefeatCount(home.getDefeatCount() + 1);
        } else {
            home.setPoints(home.getPoints() + 1);
            away.setPoints(away.getPoints() + 1);
            home.setDrawCount(home.getDrawCount() + 1);
            away.setDrawCount(away.getDrawCount() + 1);
        }
    }

    private void displayCalendar() {
        System.out.println("Display Calendar and Find Match (press 6)");
    }


    public void playerTrans() {
        /*
        int i=0;
        for (FootballTeam club : teams) {
            for (Player p : club.getSubstitutePlayers()) {
                unassignedPlayers.add(p);
                }
        }
        unassignedPlayers.sort(new PlayerComparator());
        for(Player p: unassignedPlayers){
            System.out.print(i+" "+p);
        }
        System.out.print("Choose the Player you want to transfer:");
        //System.out.println("Choose the Player you want to transfer: ");
        //int elast = Integer.parseInt(scanner.nextLine());
         */

        System.out.println("Choose the team of origin");
        printTeams();
        int teamIndex = Integer.parseInt(scanner.nextLine());
        Team teamOrigin = teams.get(teamIndex);
        System.out.println("Choose the player you want to transfer"); // TODO: Definir '0: ' para voltar para trás
        printPlayers(teamOrigin);
        int playerIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Player player = teamOrigin.getPlayers().get(playerIndex);
        teamOrigin.removePlayer(player);
        System.out.println("Choose destination team");
        printTeams();
        teamIndex = Integer.parseInt(scanner.nextLine());
        Team teamDestination = teams.get(teamIndex);
        teamDestination.addPlayer(player);
        anyKey();


    }

    private void printPlayers(Team team) {
        AtomicInteger count=new AtomicInteger(1);
        team.getPlayers()
                //.sorted(new PlayerComparator())
                .forEach(p -> {
                    System.out.println(count + ": " + p.getName() + " Ability: " + p.getAbility());
                    count.getAndIncrement();
                });
    }

}