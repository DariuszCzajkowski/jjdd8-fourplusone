package com.infoshareacademy.service;

import com.infoshareacademy.dao.BookDao;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class PaginationService {

    @Inject
    BookDao bookDao;

    public int add(int num) {
        return num + 20;
    }

    public int reduce(int num) {
        return num - 20;
    }

    public int getLastPage(){return bookDao.getNumberOfRecords() - 20;}

    public int getLasPageAudio() {return  bookDao.getNumberOfAudioBooks() - 20;}

    public int getLasPageEpic() {return  bookDao.getNumberOfEpicBooks() - 20;}

    public int getLasPageLyric() {return  bookDao.getNumberOfLyricBooks() - 20;}

    public int getLasPageDrama() {return  bookDao.getNumberOfDramaBooks() - 20;}

}
