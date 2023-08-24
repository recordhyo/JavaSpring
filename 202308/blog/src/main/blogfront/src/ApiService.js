import axios from 'axios';

const BLOG_API_URL = "http://localhost:8080/api/articles"

class ApiService {
    fetchArticles() {
        return axios.get(BLOG_API_URL);
    }
    fetchArticleById(id){
        return axios.get(BLOG_API_URL+"/"+id)
    }

    deleteArticle(id){
        return axios.delete(BLOG_API_URL+"/"+id)
    }

    addArticle(Article){
        return axios.post(BLOG_API_URL, Article);
    }

    editArticle(Article){
        return axios.put(BLOG_API_URL, Article);
    }
}

export default new ApiService();