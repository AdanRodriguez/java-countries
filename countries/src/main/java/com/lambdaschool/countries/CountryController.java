package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/data")
public class CountryController
{
 //---1---   //Get the names of all countries in alphabetical order
    //http://localhost:2019/data/names/all
    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> getAllCountries()
    {
        //Sorting in alphabetical order
        CountriesApplication.ourCountryList.countryList.sort((c1 ,c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList, HttpStatus.OK);
    }
 //---2---   //Get countries by letter and return alphabetically
    //http://localhost:2019/data/names/start/{letter}
    @GetMapping(value = "/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesByLetter(@PathVariable char letter)
    {
        ArrayList<Country> rtnCountry = CountriesApplication.ourCountryList.findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));

        //Sorting in alphabetical order
        rtnCountry.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }
//---3---   //Get countries equal to or longer than the given length in alphabetical order
    //http://localhost:2019/data/names/size/{number}
    @GetMapping(value = "/names/size/{number}", produces = {"application/json"})
        public ResponseEntity<?> getCountriesByLength(@PathVariable int number)
    {
        ArrayList<Country> rtnCountry = CountriesApplication.ourCountryList.findCountries(c -> c.getName().length() >= number);
        //Sorting in alphabetical order
        rtnCountry.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }
//---4---   //Get countries equal to or longer than the given number
    //http://localhost:2019/data/population/size/{people}
    @GetMapping(value = "/population/size/{people}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesByPopulation(@PathVariable long people)
    {
        ArrayList<Country> rtnCountry = CountriesApplication.ourCountryList.findCountries(c -> c.getPopulation() >= people);
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }
//---5---   //Get country with the lowest population
    //http://localhost:2019/data/population/min
    @GetMapping(value = "/population/min", produces = {"application/json"})
    public ResponseEntity<?> getLowestPopulation()
    {
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(0), HttpStatus.OK);
    }
//---6---   //Get country with the highest population
    //http://localhost:2019/data/population/max
    @GetMapping(value = "/population/max", produces = {"application/json"})
    public ResponseEntity<?> getLargestPopulation()
    {
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> (int)(c2.getPopulation() - c1.getPopulation()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList.get(0), HttpStatus.OK);
    }








}
