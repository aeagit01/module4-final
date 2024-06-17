package com.javarush.dao;

import com.javarush.domain.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityDAOTest {

    private CityDAO cityDAO;
    private SessionFactory sessionFactory;
    private Query<City> query;
    private Query<Long> queryLong;


    @BeforeEach
    void setUp() {
        sessionFactory = mock(SessionFactory.class);
        cityDAO = new CityDAO(sessionFactory);
        query = mock(Query.class);
        queryLong = mock(Query.class);
    }

    @Test
    void testGetById() {
        City expectedCity = new City();
        expectedCity.setId(1);
        expectedCity.setName("New York");

        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :ID", City.class)).thenReturn(query);
        when(query.setParameter("ID", 1)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedCity);

        City actualCity = cityDAO.getById(1);

        assertEquals(expectedCity, actualCity);
    }

    @Test
    void testGetItems_PositiveOffsetLimit() {
        int offset = 1;
        int limit = 5;

        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery(anyString(), eq(City.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(new City()));

        List<City> result = cityDAO.getItems(offset, limit);

        verify(query).setFirstResult(offset);
        verify(query).setMaxResults(limit);
        verify(query).list();
    }

    @Test
    void testGetItems_ZeroOffsetPositiveLimit() {
        int offset = 0;
        int limit = 5;

        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery(anyString(), eq(City.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(new City()));

        List<City> result = cityDAO.getItems(offset, limit);

        verify(query).setFirstResult(offset);
        verify(query).setMaxResults(limit);
        verify(query).list();
    }

    @Test
    void testGetItems_PositiveOffsetZeroLimit() {
        int offset = 5;
        int limit = 0;

        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery(anyString(), eq(City.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(new City()));

        List<City> result = cityDAO.getItems(offset, limit);

        verify(query).setFirstResult(offset);
        verify(query).setMaxResults(limit);
        verify(query).list();
    }

    @Test
    void testGetItems_ZeroOffsetZeroLimit() {
        int offset = 0;
        int limit = 0;

        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery(anyString(), eq(City.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(new City()));

        List<City> result = cityDAO.getItems(offset, limit);

        verify(query).setFirstResult(offset);
        verify(query).setMaxResults(limit);
        verify(query).list();
    }

    @Test
    void testGetItems_NegativeOffsetPositiveLimit() {
        int offset = -1;
        int limit = 5;

        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery(anyString(), eq(City.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(new City()));

        List<City> result = cityDAO.getItems(offset, limit);

        verify(query).setFirstResult(offset);
        verify(query).setMaxResults(limit);
        verify(query).list();
    }

    @Test
    void testGetItems_PositiveOffsetNegativeLimit() {
        int offset = 5;
        int limit = -1;

        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery(anyString(), eq(City.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(new City()));

        List<City> result = cityDAO.getItems(offset, limit);

        verify(query).setFirstResult(offset);
        verify(query).setMaxResults(limit);
        verify(query).list();
    }
    @Test
    void testGetTotalCount_ValidCountValue() {
        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery("select count(c) from City c", Long.class)).thenReturn(queryLong); //
        when(queryLong.uniqueResult()).thenReturn(10L);

        int result = cityDAO.getTotalCount();

        assertEquals(10, result);
    }

    @Test
    void testGetTotalCount_ZeroCountValue() {
        Long zero = 0L;
        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery("select count(c) from City c", Long.class)).thenReturn(queryLong);
        when(queryLong.uniqueResult()).thenReturn(zero);

        int result = cityDAO.getTotalCount();

        assertEquals(0, result);
    }
}