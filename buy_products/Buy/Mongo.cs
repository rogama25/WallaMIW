using MongoDB.Driver;

namespace Buy
{
    public class Mongo
    {
        private readonly MongoClient mongo;
        private readonly string database;
        private readonly string collection;

        public Mongo(MongoClient mongo, string database, string collection)
        {
            this.mongo = mongo;
            this.database = database;
            this.collection = collection;
        }
        public Product GetProduct(string id)
        {
            return mongo.GetDatabase(database).GetCollection<Product>(collection).Find(product => product.Id == id).FirstOrDefault();
        }

        public bool ReduceStockProduct(string id, int amountReduced)
        {
            return mongo.GetDatabase(database).GetCollection<Product>(collection).UpdateOne(product => product.Id == id && product.Stock >= amountReduced,
                Builders<Product>.Update.Inc(product => product.Stock, -amountReduced)).MatchedCount > 0;
        }
    }
}
