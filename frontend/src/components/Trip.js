import "../styles/TripStyles.css";
import TripData from "./TripData";
import image1 from "../assets/mumbaiImg2.jpg";
import image2 from "../assets/trip2.jpg";
import image3 from "../assets/trip3.jpg";

const Trip = () => {
  return (
    <div className="trip">
      <h1>Recent Trips</h1>
      <p>You can discover unique destinations.</p>
      <div className="tripcard">
        <TripData
          image={image1}
          heading="Trip in Europe"
          text="Discover the best destinations for Christmas, the most beautiful beaches in Europe, the best summer festivals, the best family destinations, the most romantic destinations, the hidden gems or the best fairytale destinations and many more. There is so much to discover in Europe!"
        />
        <TripData
          image={image2}
          heading="Trip in Bali"
          text="Bali Island is a small beautiful island and a part of Indonesia archipelago, and the most famous of Indonesian tourism in the world. It owns the panorama and unique culture that make this island is exclusive than others. Furthermore, It's location is in the tropical situation as Dream Island for a vacation."
        />
        <TripData
          image={image3}
          heading="Trip in Maldives"
          text="Maldives Overview The Maldives consists of 1,190 islands located in the Indian Ocean, southwest of India. They offer seclusion, breathtaking ocean views, and the most luxurious overwater villas in the World. Amazing scuba diving, snorkeling, and surfing help make the Maldives a unique and fantastic beach destination."
        />
      </div>
    </div>
  );
};

export default Trip;
