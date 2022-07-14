package api.pojos;

import java.util.List;
import java.util.Map;

public class PetPojo {
    private int id;
    private PetCategoryPojo category;
    private String name;
    private List<String> photoUrls;
    private List<Map<String,Object>> tags;
    private String status;


    public PetPojo(int id,String name, String status){
        this.setId(id);
        setName(name);
        this.status=status;
    }


    public int getId() {
        return id;
    }

    public PetCategoryPojo getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<Map<String, Object>> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(PetCategoryPojo category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setTags(List<Map<String, Object>> tags) {
        this.tags = tags;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
