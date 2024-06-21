const delivery = {
  city: 'Seropédca',
  state: 'RJ',
  time: 11,
  value: 18.47
}

const useDelivery = () => {
  const getDeliveryInfo = async (cep: string) => {
    return new Promise((res, rej) => {
      res(delivery);
    })
  };

  return {
    getDeliveryInfo
  };
};

export default useDelivery;