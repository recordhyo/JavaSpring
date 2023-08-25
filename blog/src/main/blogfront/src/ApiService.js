import axios from 'axios';

const BLOG_API_URL = "http://localhost:8080/api/articles"

class ApiService {
    LoadArticles() {
        return axios.get(BLOG_API_URL);
    }
    fetchArticleById(id){
        return axios.get(`http://localhost:8080/api/articles/${id}`)
    }

    deleteArticle(id){
        return axios.delete(`http://localhost:8080/api/articles/${id}`)
    }

    addArticle(Article){
        return axios.post(BLOG_API_URL, Article);
    }

    editArticle(Article){
        return axios.patch(BLOG_API_URL, Article);
    }
}

export default new ApiService();