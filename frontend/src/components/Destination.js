import "../styles/DestinationStyles.css";
import DestinationData from "./DestinationData";
import puneImg1 from "../assets/puneImg1.jpg";
import puneImg2 from "../assets/puneImg2.jpg";
import mumbaiImg1 from "../assets/mumbaiImg1.jpg";
import mumbaiImg2 from "../assets/mumbaiImg2.jpg";

const Destination = () => {
  return (
    <div className="destination">
      <h1>Popular Destination</h1>
      <p>Tours give you the opportunity to see a lot, within a time frame.</p>

      <DestinationData
        className="first-des"
        heading="Pune"
        text="Pune, also called Poona, city, west-central Maharashtra state, western India, at the junction of the Mula and Mutha rivers. Called “Queen of the Deccan,” Pune is the cultural capital of the Maratha peoples. The city first gained importance as the capital of the Bhonsle Marathas in the 17th century."
        img1={puneImg1}
        img2={puneImg2}
      />

      <DestinationData
        className="first-des-reverse"
        heading="Mumbai"
        text="Mumbai is the centre of the Mumbai Metropolitan Region, the sixth most populous metropolitan area in the world with a population of over 23 million (2.3 crore). Mumbai lies on the Konkan coast on the west coast of India and has a deep natural harbour. In 2008, Mumbai was named an alpha world city."
        img1={mumbaiImg1}
        img2={mumbaiImg2}
      />
    </div>
  );
};

export default Destination;
