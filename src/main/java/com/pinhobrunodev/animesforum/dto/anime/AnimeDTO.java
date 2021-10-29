package com.pinhobrunodev.animesforum.dto.anime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pinhobrunodev.animesforum.dto.gender.GenderDTO;
import com.pinhobrunodev.animesforum.entities.Anime;
import com.pinhobrunodev.animesforum.entities.Gender;
import com.pinhobrunodev.animesforum.validations.anime.AnimeInsertValid;
import com.pinhobrunodev.animesforum.validations.anime.AnimeUpdateValid;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AnimeDTO {

    private Long id;
    @NotBlank(message = "Mandatory field.")
    private String title;
    @NotBlank(message = "Mandatory field.")
    private String description;
    @NotBlank(message = "Mandatory field.")
    private String imgUrl;
    @NotNull(message = "Mandatory field.")
    //@Past(message = "Date can't be future.")
    @PastOrPresent(message = "Date can't be future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    private List<GenderDTO> genders = new ArrayList<>();


    public AnimeDTO(Anime entity){
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        imgUrl = entity.getImgUrl();
        releaseDate = entity.getReleaseDate();
    }

    public AnimeDTO(Anime entity, Set<Gender> genderEntity){
        this(entity);
        genderEntity.forEach(x->genders.add(new GenderDTO(x)));
    }


    public AnimeDTO() {
    }

    public AnimeDTO(String title, String description, String imgUrl, LocalDate releaseDate) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.releaseDate = releaseDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<GenderDTO> getGenders() {
        return genders;
    }
}
