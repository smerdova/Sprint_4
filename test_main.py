from main import BooksCollector
import pytest


class TestBooksCollector:

    @pytest.mark.parametrize(
        'name, expected_length',
        [
            ['Евгений Онегин', 1],
            ['Что делать, если ваш кот хочет вас убить 2', 0]
        ]
    )
    def test_add_new_book(self, name, expected_length):

        collector = BooksCollector()

        collector.add_new_book(name)
        
        assert len(collector.books_genre) == expected_length


    def test_set_book_genre_add_one_genre_positive_result(self):
        
        collector = BooksCollector()
        collector.add_new_book('Код да Винчи')

        collector.set_book_genre('Код да Винчи', 'Детективы')
        
        assert 'Детективы' in collector.books_genre.values()

    def test_get_book_genre_positive_result(self):
        
        collector = BooksCollector()
        collector.add_new_book('Дюна')
        collector.set_book_genre('Дюна', 'Фантастика')

        actual_result = collector.get_book_genre('Дюна')
        
        assert actual_result == 'Фантастика'

    @pytest.mark.parametrize(
        'genre, books_with_specific_genre',
        [
            ['Фантастика', ['Дюна', 'Задача трех тел']],
            ['Детективы', ['Код да Винчи']]
        ]
    )
    def test_get_books_with_specific_genre_check_result(self, genre, books_with_specific_genre):
        
        collector = BooksCollector()
        collector.add_new_book('Код да Винчи')
        collector.add_new_book('Дюна')
        collector.add_new_book('Задача трех тел')
        collector.set_book_genre('Код да Винчи', 'Детективы')
        collector.set_book_genre('Дюна', 'Фантастика')
        collector.set_book_genre('Задача трех тел', 'Фантастика')

        actual_result = collector.get_books_with_specific_genre(genre)

        assert all(item in books_with_specific_genre for item in actual_result)

    @pytest.mark.parametrize(
        'genre, books_not_in_result',
        [
            ['Фантастика', ['Код да Винчи']],
            ['Детективы', ['Задача трех тел', 'Дюна']]
        ]
    )
    def test_get_books_with_specific_genre_not_in_result(self, genre, books_not_in_result):
        
        collector = BooksCollector()
        collector.add_new_book('Код да Винчи')
        collector.add_new_book('Дюна')
        collector.add_new_book('Задача трех тел')
        collector.set_book_genre('Код да Винчи', 'Детективы')
        collector.set_book_genre('Дюна', 'Фантастика')
        collector.set_book_genre('Задача трех тел', 'Фантастика')

        actual_result = collector.get_books_with_specific_genre(genre)

        assert books_not_in_result not in actual_result

    def test_get_books_genre_get_three_elements_positive_result(self):
        
        collector = BooksCollector()
        collector.add_new_book('Код да Винчи')
        collector.add_new_book('Дюна')
        collector.add_new_book('Задача трех тел')
        collector.set_book_genre('Код да Винчи', 'Детективы')
        collector.set_book_genre('Дюна', 'Фантастика')
        collector.set_book_genre('Задача трех тел', 'Фантастика')

        actual_result = collector.get_books_genre()

        assert len(actual_result) == 3

    def test_get_books_for_children_add_three_books_positive_result(self):
        
        collector = BooksCollector()
        collector.add_new_book('Код да Винчи')
        collector.add_new_book('Дюна')
        collector.add_new_book('ОНО')
        collector.add_new_book('Цветик-семицветик')
        collector.add_new_book('12 стульев')
        collector.set_book_genre('Код да Винчи', 'Детективы')
        collector.set_book_genre('Дюна', 'Фантастика')
        collector.set_book_genre('ОНО', 'Ужасы')
        collector.set_book_genre('Цветик-семицветик', 'Мультфильмы')
        collector.set_book_genre('12 стульев', 'Комедии')

        actual_result = collector.get_books_for_children()

        assert 'Цветик-семицветик' and '12 стульев' and 'Дюна' in actual_result

    def test_get_books_for_children_filtered_adult_content(self):
        
        collector = BooksCollector()
        collector.add_new_book('Код да Винчи')
        collector.add_new_book('Дюна')
        collector.add_new_book('ОНО')
        collector.add_new_book('Цветик-семицветик')
        collector.add_new_book('12 стульев')
        collector.set_book_genre('Код да Винчи', 'Детективы')
        collector.set_book_genre('Дюна', 'Фантастика')
        collector.set_book_genre('ОНО', 'Ужасы')
        collector.set_book_genre('Цветик-семицветик', 'Мультфильмы')
        collector.set_book_genre('12 стульев', 'Комедии')

        actual_result = collector.get_books_for_children()

        assert 'ОНО' and 'Код да Винчи' not in actual_result

    def test_add_book_in_favorites_add_one_book_positive_result(self):
        
        collector = BooksCollector()
        collector.add_new_book('Код да Винчи')
        collector.add_book_in_favorites('Код да Винчи')

        assert len(collector.favorites) == 1

    def test_delete_book_from_favorites_delete_one_book_positive_result(self):
        
        collector = BooksCollector()
        collector.add_new_book('Код да Винчи')
        collector.add_book_in_favorites('Код да Винчи')

        collector.delete_book_from_favorites('Код да Винчи')

        assert len(collector.favorites) == 0

    def test_get_list_of_favorites_books_get_favorites_books_positive_result(self):
        
        collector = BooksCollector()
        collector.add_new_book('Код да Винчи')
        collector.add_new_book('Дюна')
        collector.add_book_in_favorites('Код да Винчи')
        collector.add_book_in_favorites('Дюна')

        actual_result = collector.get_list_of_favorites_books()

        assert 'Дюна' and 'Код да Винчи' in actual_result
