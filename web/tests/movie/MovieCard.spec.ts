import { mount } from "@vue/test-utils";
import MovieCard from "../../src/movie/MovieCard.vue";
import { describe, it, expect } from "vitest";

describe("HelloWorld.vue", () => {

    it("should render card", () => {
        const wrapper = mount(MovieCard, {});
        const titleSelector = '[ data-test-id=movie-title]'
        expect(wrapper.find(titleSelector).text()).toContain('Movie Name')
    });
});