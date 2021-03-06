package com.infoshareacademy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.infoshareacademy.SortType.ASC;

public class BookSorter {

    private AppPropertiesReader propertiesReader = new AppPropertiesReader();

    public List<Book> sortingByTitle(List<Book> books) {
        if (propertiesReader.getProp("sortByTitle").equals(ASC.toString())) {
            return books.stream()
                    .sorted(Comparator.comparing(Book::getTitle))
                    .collect(Collectors.toList());
        } else {
            return books.stream()
                    .sorted(Comparator.comparing(Book::getTitle).reversed())
                    .collect(Collectors.toList());
        }
    }

    public List<Book> sortingByAuthor(List<Book> books) {
        if (propertiesReader.getProp("sortByAuthor").equals(ASC.toString())) {
            return books.stream()
                    .sorted(Comparator.comparing(Book::getAuthor))
                    .collect(Collectors.toList());
        } else {
            return books.stream()
                    .sorted(Comparator.comparing(Book::getAuthor).reversed())
                    .collect(Collectors.toList());
        }
    }

    public List<Book> sortingByEpoch(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getEpoch))
                .collect(Collectors.toList());
    }

    public List<Book> sortingByGenre(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getGenre))
                .collect(Collectors.toList());
    }

    public List<Book> sortingByKind(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getKind))
                .collect(Collectors.toList());
    }
}