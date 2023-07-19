package ru.skypro.homework.mapper.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ads.CreateOrUpdateAds;
import ru.skypro.homework.dto.ads.ResponseAd;
import ru.skypro.homework.dto.ads.ResponseFullAd;
import ru.skypro.homework.dto.ads.ResponseWrapperAds;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.service.impl.UserService;

import java.util.List;

@Mapper(componentModel="spring", uses = UserService.class)
public interface AdsMapperMapStruct {

    @Mapping(source = "author.id", target = "author")
    @Mapping(target = "image", expression  = "java(\"/images/\" + ad.getImage().getFileName())")
    @Mapping(source = "id", target = "pk")
    ResponseAd adToResponseAd(Ad ad);

    @Mapping(expression = "java(results.size())", target = "count")
    default ResponseWrapperAds adsToResponseWrapperAds(List<Ad> results){
        return adsToResponseWrapperAds(results.size(),results);
    }

    ResponseWrapperAds adsToResponseWrapperAds(int count, List<Ad> results);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(target = "image", expression  = "java(\"/images/\" + ad.getImage().getFileName())")
    @Mapping(source = "author.email", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    ResponseFullAd adToResponseFullAd(Ad ad);

    default Ad createOrUpdateAdsToAd(Ad ad, CreateOrUpdateAds updateAd){
        ad.setDescription(updateAd.getDescription());
        ad.setPrice(updateAd.getPrice());
        ad.setTitle(updateAd.getTitle());
        return ad;
    }
}

