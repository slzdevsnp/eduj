package com.baeldung.java_8_features.groupingby;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@Slf4j
public class Java8GroupingByCollectorBlogPostTest {

    private static final List<BlogPost> posts = Arrays.asList(
            new BlogPost("News item 1", "Author 1", BlogPostType.NEWS, 15),
            new BlogPost("Tech review 1", "Author 2", BlogPostType.REVIEW, 5),
            new BlogPost("Programming guide", "Author 1", BlogPostType.GUIDE, 20),
            new BlogPost("News item 2", "Author 2", BlogPostType.NEWS, 35),
            new BlogPost("Tech review 2", "Author 1", BlogPostType.REVIEW, 15),
            new BlogPost("News item 3", "Author 2", BlogPostType.NEWS, 30),
            new BlogPost("Story item 1", "Author 2", BlogPostType.STORY, 100)

    );


    @Test
    public void givenAListOfPosts_whenGroupedByType_thenGetAMapBetweenTypeAndPosts() {
        Map<BlogPostType,List<BlogPost>> postsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType));
        //asserts
        assertThat(postsPerType.get(BlogPostType.NEWS).size(), is(3));
        assertThat(postsPerType.get(BlogPostType.GUIDE).size(), is(1));

    }

    @Test
    public void givenAListOfPosts_whenGroupedByComplexMapKeyType_thenGetAMapBetweenTupleAndList()
    {
        //group by complex multi field tuple
        Map<BlogPostTypeAuthorTuple,List<BlogPost>> postsPerTypeAuthor = posts.stream()
                .collect(groupingBy(p-> new BlogPostTypeAuthorTuple(p.getType(),p.getAuthor())));

        //asserts
        assertThat(postsPerTypeAuthor.get(new BlogPostTypeAuthorTuple(BlogPostType.GUIDE, "Author 1")).size(),
                is(1));
        assertThat(postsPerTypeAuthor.get(new BlogPostTypeAuthorTuple(BlogPostType.NEWS, "Author 2")).size(),
                is(2));
    }

    @Test
    public void givenAListOfPosts_whenGroupedByTypeInSets_thenGetAMapBetweenTypesAndSetsOfPosts() {
        //modifiy the returned map value type
        Map<BlogPostType, Set<BlogPost>> postsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType, toSet()));

        assertEquals(3, postsPerType.get(BlogPostType.NEWS)
                .size());
        assertEquals(1, postsPerType.get(BlogPostType.GUIDE)
                .size());
        assertEquals(2, postsPerType.get(BlogPostType.REVIEW)
                .size());
    }

    @Test
    public void givenAListOfPosts_whenGroupedByAuthorAndThenByType_thenGetAMapBetweenAuthorAndMapsBetweenTypeAndBlogPosts() {
        Map<String, Map<BlogPostType, List<BlogPost>>> map = posts.stream()
                .collect(groupingBy(BlogPost::getAuthor, groupingBy(BlogPost::getType)));

        assertEquals(1, map.get("Author 1")
                .get(BlogPostType.NEWS)
                .size());
        assertEquals(1, map.get("Author 1")
                .get(BlogPostType.GUIDE)
                .size());
        assertEquals(1, map.get("Author 1")
                .get(BlogPostType.REVIEW)
                .size());

        assertEquals(2, map.get("Author 2")
                .get(BlogPostType.NEWS)
                .size());
        assertEquals(1, map.get("Author 2")
                .get(BlogPostType.REVIEW)
                .size());
        assertNull(map.get("Author 2")
                .get(BlogPostType.GUIDE));
    }

    @Test
    public void givenAListOfPosts_whenGroupedByTypeAndAveragingLikes_thenGetAMapBetweenTypeAndAverageNumberOfLikes() {
        //average from group result
        Map<BlogPostType, Double> averageLikesPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType, averagingInt(BlogPost::getLikes)));

        assertEquals(26, averageLikesPerType.get(BlogPostType.NEWS)
                .intValue());
        assertEquals(20, averageLikesPerType.get(BlogPostType.GUIDE)
                .intValue());
        assertEquals(10, averageLikesPerType.get(BlogPostType.REVIEW)
                .intValue());
    }


    @Test
    public void givenAListOfPosts_whenGroupedByTypeAndSumTheLikes_thenGetAMapBetweenTypeAndPostLikes() {
        //summing number of likes per blogpost type
        Map<BlogPostType, Integer> totalLikesPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType, summingInt(BlogPost::getLikes)));

        assertEquals(80, totalLikesPerType.get(BlogPostType.NEWS)
                .intValue());
        assertEquals(20, totalLikesPerType.get(BlogPostType.REVIEW)
                .intValue());
        assertEquals(20, totalLikesPerType.get(BlogPostType.GUIDE)
                .intValue());
    }

    @Test
    public void givenAListOfPosts_whenGroupedByTypeAndMaxingLikes_thenGetAMapBetweenTypeAndMaximumNumberOfLikes() {
        Map<BlogPostType, Optional<BlogPost>> maxLikesPerPostType = posts.stream()
                .collect(groupingBy(BlogPost::getType, maxBy(comparingInt(BlogPost::getLikes))));

        assertTrue(maxLikesPerPostType.get(BlogPostType.NEWS)
                .isPresent());
        assertEquals(35, maxLikesPerPostType.get(BlogPostType.NEWS)
                .get()
                .getLikes());

        assertTrue(maxLikesPerPostType.get(BlogPostType.GUIDE)
                .isPresent());
        assertEquals(20, maxLikesPerPostType.get(BlogPostType.GUIDE)
                .get()
                .getLikes());

        assertTrue(maxLikesPerPostType.get(BlogPostType.REVIEW)
                .isPresent());
        assertEquals(15, maxLikesPerPostType.get(BlogPostType.REVIEW)
                .get()
                .getLikes());

        assertTrue(maxLikesPerPostType.get(BlogPostType.STORY)
                .isPresent());

    }

    @Test
    public void givenAListOfPosts_whenGroupedByTypeAndSummarizingLikes_thenGetAMapBetweenTypeAndSummary() {
        Map<BlogPostType, IntSummaryStatistics> likeStatisticsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType, summarizingInt(BlogPost::getLikes)));

        IntSummaryStatistics newsLikeStatistics = likeStatisticsPerType.get(BlogPostType.NEWS);

        assertEquals(3, newsLikeStatistics.getCount());
        assertEquals(80, newsLikeStatistics.getSum());
        assertEquals(26.666, newsLikeStatistics.getAverage(), 0.001);
        assertEquals(35, newsLikeStatistics.getMax());
        assertEquals(15, newsLikeStatistics.getMin());
    }

    @Test
    public void givenAListOfPosts_whenGroupedByTypeAndTheirTitlesAreJoinedInAString_thenGetAMapBetweenTypeAndCsvTitles() {
        //mapping grouped results to a different type.  (String in this example)
        Map<BlogPostType, String> postsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType,
                                    mapping(BlogPost::getTitle,
                                            joining(", ", "Post titles: [", "]"))));

        assertEquals("Post titles: [News item 1, News item 2, News item 3]",
                postsPerType.get(BlogPostType.NEWS));
        assertEquals("Post titles: [Programming guide]",
                postsPerType.get(BlogPostType.GUIDE));
        assertEquals("Post titles: [Tech review 1," +
                " Tech review 2]", postsPerType.get(BlogPostType.REVIEW));
    }
    @Test
    public void givenAListOfPosts_whenGroupedByTypeInAnEnumMap_thenGetAnEnumMapBetweenTypeAndPosts() {

        EnumMap<BlogPostType, List<BlogPost>> postsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType, () -> new EnumMap<>(BlogPostType.class), toList()));

        assertEquals(3, postsPerType.get(BlogPostType.NEWS)
                .size());
        assertEquals(1, postsPerType.get(BlogPostType.GUIDE)
                .size());
        assertEquals(2, postsPerType.get(BlogPostType.REVIEW)
                .size());

    }

    @Test
    public void givenAListOfPosts_whenGroupedByTypeConcurrently_thenGetAMapBetweenTypeAndPosts() {
        //multi-core processing
        ConcurrentMap<BlogPostType, List<BlogPost>> postsPerType = posts.parallelStream()
                .collect(groupingByConcurrent(BlogPost::getType));

        assertEquals(3, postsPerType.get(BlogPostType.NEWS)
                .size());
        assertEquals(1, postsPerType.get(BlogPostType.GUIDE)
                .size());
        assertEquals(2, postsPerType.get(BlogPostType.REVIEW)
                .size());
    }

   //java 9 filtering before grouping
   @Test
   public void givenList_whenSatifyPredicate_thenMapValueWithOccurences() {
       List<Integer> numbers = List.of(1, 2, 3, 5, 5, 7, 7, 7);

       Map<Integer, Long> result = numbers.stream()
               .filter(val -> val > 3)
               .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

       log.debug("filter 1 result - {}",result);

       assertEquals(2, result.size());

       result = numbers.stream()
               .collect(Collectors.groupingBy(i -> i,
                       Collectors.filtering(val -> val > 3, Collectors.counting())));

       log.debug("filter 2 result - {}",result);

       assertEquals(5, result.size());
   }

   //java 9 flatmapping
   @Test
   public void givenListOfBlogs_whenAuthorName_thenMapAuthorWithComments() {
       Blog blog1 = new Blog("1", "Nice", "Very Nice");
       Blog blog2 = new Blog("2", "Disappointing", "Ok", "Could be better");
       Blog blog3 = new Blog("1", "Bad", "Very Bad");
       List<Blog> blogs = List.of(blog1, blog2, blog3);

       Map<String,  List<List<String>>> authorComments1 = blogs.stream()
               .collect(Collectors.groupingBy(Blog::getAuthorName,
                       Collectors.mapping(Blog::getComments, Collectors.toList())));
       //loggin
       log.debug("authorComments1 - {}",authorComments1);

       assertEquals(2, authorComments1.size());
       assertEquals(2, authorComments1.get("1").get(0).size());
       assertEquals(3, authorComments1.get("2").get(0).size());

       Map<String, List<String>> authorComments2 = blogs.stream()
               .collect(Collectors.groupingBy(Blog::getAuthorName,
                       Collectors.flatMapping(blog -> blog.getComments().stream(),
                               Collectors.toList())));

       log.debug("authorComments2 - {}",authorComments2);

       assertEquals(2, authorComments2.size());
       assertEquals(4, authorComments2.get("1").size());
       assertEquals(3, authorComments2.get("2").size());
   }

   // weighted average with stream, google: java api stream group by weighted average
   //  https://stackoverflow.com/questions/40420069/calculate-weighted-average-with-java-8-streams

}
