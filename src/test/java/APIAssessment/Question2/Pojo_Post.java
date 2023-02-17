package APIAssessment.Question2;

public class Pojo_Post {

    int logo;
    String name;
    String doc_link;
    String description;
    int tech_type_id;
    String assoc_tags[];

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoc_link() {
        return doc_link;
    }

    public void setDoc_link(String doc_link) {
        this.doc_link = doc_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTech_type_id() {
        return tech_type_id;
    }

    public void setTech_type_id(int tech_type_id) {
        this.tech_type_id = tech_type_id;
    }

    public String[] getAssoc_tags() {
        return assoc_tags;
    }

    public void setAssoc_tags(String[] assoc_tags) {
        this.assoc_tags = assoc_tags;
    }
}
