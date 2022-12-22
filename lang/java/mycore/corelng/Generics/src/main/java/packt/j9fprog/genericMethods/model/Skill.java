package packt.j9fprog.genericMethods.model;

public class Skill {
    private final String skillName;
    private final String skillCategory;
    private final int skillRank;

    public Skill(String skillName,String skillCategory, int skillRank){
        this.skillName=skillName;
        this.skillCategory=skillCategory;
        this.skillRank=skillRank;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getSkillCategory() {
        return skillCategory;
    }

    public int getSkillRank() {
        return skillRank;
    }
}
