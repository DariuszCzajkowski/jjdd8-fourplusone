package com.infoshareacademy.service;

import com.infoshareacademy.dao.AuthorDao;
import com.infoshareacademy.dao.BookDao;
import com.infoshareacademy.domain.entity.*;
import com.infoshareacademy.domain.view.BookView;
import com.infoshareacademy.dto.BookDTO;
import com.infoshareacademy.mapper.BookMapper;
import com.infoshareacademy.mapper.view.BookMapperToView;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class BookService {

    @Inject
    private BookDao bookDao;

    @Inject
    private AuthorDao authorDao;

    @Inject
    private BookMapper bookMapper;

    @Inject
    private BookMapperToView bookMapperToView;

    @Inject
    private AuthorService authorService;

    @Inject
    private KindService kindService;

    @Inject
    private EpochService epochService;

    @Inject
    private GenreService genreService;


    public void addBooks (List<BookDTO> books)  {

        books
                .forEach(this::addBook);
    }

    public void addBook(BookDTO book) {

        String[] authorsNames;
        List<Author> authors = new ArrayList<>();

        String authorName = book.getAuthor();

        authorsNames = authorName.split(",");

        Arrays.stream(authorsNames)
                .forEach(a -> authors.add(authorService.findOrAdd(a.trim()))
        );

        String kindName = book.getKind();
        LiteratureKind kind = kindService.findOrAdd(kindName);

        String epochName = book.getEpoch();
        Epoch epoch = epochService.findOrAdd(epochName);

        String genreName = book.getGenre();
        Genre genre = genreService.findOrAdd(genreName);

        Book bookDaoToEntity = new Book();

        authors.forEach(a -> bookDaoToEntity.setAuthor(a));
        //bookDaoToEntity.setAuthor(author);
        bookDaoToEntity.setKind(kind);
        bookDaoToEntity.setEpoch(epoch);
        bookDaoToEntity.setGenre(genre);
        bookDaoToEntity.setTitle(book.getTitle());
        bookDaoToEntity.setCover(book.getCover());
        bookDaoToEntity.setCoverThumb(book.getCoverThumb());
        bookDaoToEntity.setSimpleThumb(book.getSimpleThumb());
        bookDaoToEntity.setHasAudio(book.getHasAudio());

        bookDao.addBook(bookDaoToEntity);
    }


    public Book getById(Long id) {
        return this.bookDao.findById(id);
    }


    public List<com.infoshareacademy.domain.view.BookView> findByTitle(String inputParam) {
        List<Book> bookList = bookDao.findByTitle(inputParam);
        return bookList.stream()
                .map(b -> bookMapperToView.mapEntityToView(b))
                .collect(Collectors.toList());
    }

    @Transactional
    public BookView getBookViewById(Long id) {
        Book book = getById(id);
        return bookMapperToView.mapEntityToView(book);
    }

    @Transactional
    public List<BookView> getBooksForPagination(int in) {

        List<Book> bbb = bookDao.getBooksForPagination(in);
        return bbb.stream().map(book -> bookMapperToView.mapEntityToView(book))
                .collect(Collectors.toList());

    }

    public void update(Long bookId, BookDTO bookDTO) {
        Book book = bookDao.findById(bookId);

        Author author = authorDao.findAuthorByName(bookDTO.getAuthor());
        book.setAuthor(author);

        String[] authorsNames;
        List<Author> authors = new ArrayList<>();

        String authorName = bookDTO.getAuthor();

        authorsNames = authorName.split(",");

        Arrays.stream(authorsNames)
                .forEach(a -> authors.add(authorService.findOrAdd(a.trim()))
                );

        String kindName = book.getKind().getName();
        LiteratureKind kind = kindService.findOrAdd(kindName);

        String epochName = bookDTO.getEpoch();
        Epoch epoch = epochService.findOrAdd(epochName);

        String genreName = bookDTO.getGenre();
        Genre genre = genreService.findOrAdd(genreName);



        authors.forEach(book::setAuthor);
        book.setKind(kind);
        book.setEpoch(epoch);
        book.setGenre(genre);
        book.setTitle(bookDTO.getTitle());
        book.setCover(bookDTO.getCover());
        book.setCoverThumb(bookDTO.getCoverThumb());
        book.setSimpleThumb(bookDTO.getSimpleThumb());
        book.setHasAudio(bookDTO.getHasAudio());

        bookDao.update(book);
    }
    }
