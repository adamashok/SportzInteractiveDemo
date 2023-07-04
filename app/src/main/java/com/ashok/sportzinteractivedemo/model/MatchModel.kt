package com.ashok.sportzinteractivedemo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchModel(
    @SerializedName("Matchdetail")
    val matchDetail: MatchDetailInfo,
    @SerializedName("Nuggets")
    val nuggets: List<String>,
    @SerializedName("Innings")
    val innings: List<Inning>,
//    @SerializedName("Teams")
//    val teams: TeamInfo
    @SerializedName("Teams")
    val teams: Map<String, Team>

): Serializable

data class MatchDetailInfo(
    @SerializedName("Team_Home")
    val teamHome: String,
    @SerializedName("Team_Away")
    val teamAway: String,
    @SerializedName("Match")
    val match: Match,
    @SerializedName("Series")
    val series: Series,
    @SerializedName("Venue")
    val venue: Venue,
    @SerializedName("Officials")
    val officials: Officials,
    @SerializedName("Weather")
    val weather: String,
    @SerializedName("Tosswonby")
    val tossWonBy: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Status_Id")
    val statusId: String,
    @SerializedName("Player_Match")
    val playerMatch: String,
    @SerializedName("Result")
    val result: String,
    @SerializedName("Winningteam")
    val winningTeam: String,
    @SerializedName("Winmargin")
    val winMargin: String,
    @SerializedName("Equation")
    val equation: String
): Serializable

data class Match(
    @SerializedName("Livecoverage")
    val liveCoverage: String,
    @SerializedName("Id")
    val id: String,
    @SerializedName("Code")
    val code: String,
    @SerializedName("League")
    val league: String,
    @SerializedName("Number")
    val number: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Time")
    val time: String,
    @SerializedName("Offset")
    val offset: String,
    @SerializedName("Daynight")
    val dayNight: String
): Serializable

data class Series(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("Tour")
    val tour: String,
    @SerializedName("Tour_Name")
    val tourName: String
): Serializable

data class Venue(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String
): Serializable

data class Officials(
    @SerializedName("Umpires")
    val umpires: String,
    @SerializedName("Referee")
    val referee: String
): Serializable

data class Inning(
    @SerializedName("Number")
    val number: String,
    @SerializedName("Battingteam")
    val battingTeam: String,
    @SerializedName("Total")
    val total: String,
    @SerializedName("Wickets")
    val wickets: String,
    @SerializedName("Overs")
    val overs: String,
    @SerializedName("Runrate")
    val runRate: String,
    @SerializedName("Byes")
    val byes: String,
    @SerializedName("Legbyes")
    val legByes: String,
    @SerializedName("Wides")
    val wides: String,
    @SerializedName("Noballs")
    val noBalls: String,
    @SerializedName("Penalty")
    val penalty: String,
    @SerializedName("AllottedOvers")
    val allottedOvers: String,
    @SerializedName("Batsmen")
    val batsmen: List<Batsman>,
    @SerializedName("Partnership_Current")
    val partnershipCurrent: Partnership,
    @SerializedName("Bowlers")
    val bowlers: List<Bowler>,
    @SerializedName("FallOfWickets")
    val fallOfWickets: List<FallOfWicket>,
    @SerializedName("Powerplay")
    val powerplay: Powerplay
): Serializable

data class Batsman(
    @SerializedName("Name")
    val name: String,
    @SerializedName("Runs")
    val runs: String,
    @SerializedName("Balls")
    val balls: String,
    @SerializedName("Fours")
    val fours: String,
    @SerializedName("Sixes")
    val sixes: String,
    @SerializedName("StrikeRate")
    val strikeRate: String
): Serializable

data class Partnership(
    @SerializedName("Runs")
    val runs: String,
    @SerializedName("Overs")
    val overs: String,
    @SerializedName("Wickets")
    val wickets: String
): Serializable

data class Bowler(
    @SerializedName("Name")
    val name: String,
    @SerializedName("Overs")
    val overs: String,
    @SerializedName("Maidens")
    val maidens: String,
    @SerializedName("Runs")
    val runs: String,
    @SerializedName("Wickets")
    val wickets: String,
    @SerializedName("Economy")
    val economy: String
): Serializable

data class FallOfWicket(
    @SerializedName("Batsman")
    val batsman: String,
    @SerializedName("Wicket")
    val wicket: String,
    @SerializedName("Score")
    val score: String,
    @SerializedName("Overs")
    val overs: String
): Serializable

data class Powerplay(
    @SerializedName("Overs")
    val overs: String,
    @SerializedName("Maidens")
    val maidens: String,
    @SerializedName("Runs")
    val runs: String,
    @SerializedName("Wickets")
    val wickets: String
): Serializable

data class TeamInfo(
    @SerializedName("Home")
    val home: Team,
    @SerializedName("Away")
    val away: Team
)

//data class Team(
//    @SerializedName("Name")
//    val name: String,
//    @SerializedName("Id")
//    val id: String,
//    @SerializedName("ShortName")
//    val shortName: String,
//    @SerializedName("Logo")
//    val logo: String
//)


data class Team(
    @SerializedName("Name_Full")
    val nameFull: String,
    @SerializedName("Name_Short")
    val nameShort: String,
    @SerializedName("Players")
    val players: Map<String, Player>
): Serializable

data class Player(
    @SerializedName("Position")
    val position: String,
    @SerializedName("Name_Full")
    val nameFull: String,
    @SerializedName("Iskeeper")
    val isKeeper: Boolean = false,
    @SerializedName("Iscaptain")
    val isCaptain: Boolean = false,
    @SerializedName("Batting")
    val batting: BattingStats,
    @SerializedName("Bowling")
    val bowling: BowlingStats
): Serializable

data class BattingStats(
    @SerializedName("Style")
    val style: String,
    @SerializedName("Average")
    val average: String,
    @SerializedName("Strikerate")
    val strikeRate: String,
    @SerializedName("Runs")
    val runs: String
): Serializable

data class BowlingStats(
    @SerializedName("Style")
    val style: String,
    @SerializedName("Average")
    val average: String,
    @SerializedName("Economyrate")
    val economyRate: String,
    @SerializedName("Wickets")
    val wickets: String
): Serializable

